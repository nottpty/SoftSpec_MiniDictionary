package com.example.softspec.minidictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Word;

public class WordActivity extends AppCompatActivity {

    private Button backButton;
    private TextView title;
    private TextView meaning;
    private Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        word = (Word)getIntent().getSerializableExtra("word");
        setContentView(R.layout.activity_word);
        initComponents();
    }

    private void initComponents() {

        title = (TextView)findViewById(R.id.tv_title);
        meaning = (TextView)findViewById(R.id.tv_meaning);
        backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        title.setText(word.getTitle());
        meaning.setText(word.getMeaning());
    }
}
