package com.example.softspec.minidictionary.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;
import com.example.softspec.minidictionary.views.SynonymAdapter;
import com.example.softspec.minidictionary.views.WordAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class WordActivity extends AppCompatActivity {

    private Button backButton;
    private Button addSynButton;
    private TextView title;
    private TextView meaning;
    private ListView synonymListView;
    private List<String> listSyn;
    private SynonymAdapter synonymAdapter;
    private Word word;
    private boolean isFirstime = true;
    private String thisTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        initComponents();
    }

    private void initComponents() {
//        Log.e("TITLE", thisTitle);
        if(isFirstime) {
            word = (Word) getIntent().getSerializableExtra("word");
            thisTitle = word.getTitle();
            isFirstime = false;
        }
        else {
            word = Storage.getInstance().getWordByTitle(thisTitle);
        }
        listSyn = new ArrayList<String>();
        synonymAdapter  = new SynonymAdapter(this, R.layout.synonym_cell,listSyn);
        synonymListView = (ListView)findViewById(R.id.list_view_synonym);
        synonymListView.setAdapter(synonymAdapter);
        synonymListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WordActivity.this, WordActivity.class);
                intent.putExtra("word", listSyn.get(position));
                startActivity(intent);
            }
        });
        title = (TextView)findViewById(R.id.tv_title);
        meaning = (TextView)findViewById(R.id.tv_meaning);
        synonymListView = (ListView)findViewById(R.id.list_view_synonym);
        backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addSynButton = (Button) findViewById(R.id.btn_addSynonym);
        addSynButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordActivity.this, AddSynonymActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        title.setText(word.getTitle());
        meaning.setText(word.getMeaning());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(WordActivity.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Storage.getInstance().deleteWords(WordActivity.this, word);
                    Toast.makeText(WordActivity.this, "Deleting Successful", Toast.LENGTH_LONG).show();
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing
                    finish();
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

        else if(id == R.id.btn_edit) {
            Intent intent = new Intent(WordActivity.this, EditWordActivity.class);
            intent.putExtra("word", word);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_edit, menu);
        inflater.inflate(R.menu.action_delete, menu);
        return true;
    }
}
