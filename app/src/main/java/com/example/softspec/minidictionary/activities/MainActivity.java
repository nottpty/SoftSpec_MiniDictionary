package com.example.softspec.minidictionary.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;
import com.example.softspec.minidictionary.views.WordAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView wordListView;
    private List<Word> words;
    private WordAdapter wordAdapter;

//    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        for(Word word: Storage.getInstance().loadWords()){
            words.add(word);
        }
        wordAdapter.notifyDataSetChanged();
    }

    protected void onStart(){
        super.onStart();
        refreshWords();
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

        if(id == R.id.btn_more) {
            //TODO: clear all words in list.
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_add, menu);
        inflater.inflate(R.menu.action_more, menu);
        return true;
    }

}
