package com.example.softspec.minidictionary.activities;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;
import com.example.softspec.minidictionary.views.WordAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView wordListView;
    private SearchView searchView;
    private List<Word> words;
    private WordAdapter wordAdapter;
    private SharedPreferences sp;
    private boolean isFirstime = true;
    private SharedPreferences.Editor editor;

//    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("WORDS", Context.MODE_PRIVATE);
        editor = sp.edit();
        initComponents();
    }

    private void initComponents() {
        words = new ArrayList<Word>();
        wordAdapter  = new WordAdapter(this, R.layout.word_cell,words);
        wordListView = (ListView)findViewById(R.id.list_view);
        wordListView.setAdapter(wordAdapter);
        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WordActivity.class);
                intent.putExtra("word", words.get(position));
                startActivity(intent);
            }
        });
//        addButton = (Button) findViewById(R.id.btn_add);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void refreshWords(){
        words.clear();
        for(Word word: Storage.getInstance().loadWords(this)){
            words.add(word);
        }
        Collections.sort(words, new Word.AlphabetComparator());
        wordAdapter.notifyDataSetChanged();
    }

    protected void onStart(){
        super.onStart();
        if(isFirstime) {
            getWordsFromStorage();
            isFirstime = false;
        }
        refreshWords();
    }

    private void getWordsFromStorage() {
        int size = sp.getInt("words_size", 0);
        for(int i = 0; i < size; i++) {
            String title = sp.getString("title_" + i, "");
            String meaning = sp.getString("meaning_" + i, "");
            Storage.getInstance().saveWord(this, new Word(title, meaning));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_add) {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivity(intent);
        }

        if(id == R.id.clear_all) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Storage.getInstance().clearStorage(MainActivity.this);
                    refreshWords();
                    dialog.dismiss();
                }

            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
            refreshWords();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_search, menu);
        inflater.inflate(R.menu.action_add, menu);
        inflater.inflate(R.menu.action_more, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
        return true;
    }

    private void setupSearchView(MenuItem searchItem) {
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search");
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
                | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {
                if (inf.getSuggestAuthority() != null
                        && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            searchView.setSearchableInfo(info);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                words.clear();
                for (Word w : Storage.getInstance().loadWords(MainActivity.this)) {
                    if (w.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                        words.add(w);
                    }
                }

                wordAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
