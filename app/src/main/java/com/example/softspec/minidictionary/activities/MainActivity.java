package com.example.softspec.minidictionary.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;
import com.example.softspec.minidictionary.views.WordAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView wordGridView;
    private List<Word> words;
    private WordAdapter wordAdapter;

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        words = new ArrayList<Word>();
        wordAdapter  = new WordAdapter(this, R.layout.word_cell,words);
        wordGridView = (ListView)findViewById(R.id.list_view);
        wordGridView.setAdapter(wordAdapter);
        wordGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WordActivity.class);
                intent.putExtra("word", words.get(position));
                startActivity(intent);
            }
        });
        addButton = (Button) findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivity(intent);
            }
        });
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
}
