package com.example.softspec.minidictionary.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;

public class NewWordActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button saveButton;
    private TextView word;
    private TextView meaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        initComponents();
    }

    private void initComponents() {
        word = (TextView) findViewById(R.id.edtxt_title);
        meaning  = (TextView) findViewById(R.id.edtxt_meaning);
        // TODO: save the new word into storage (if user clicks "SAVE").
        saveButton = (Button) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewWord();
                finish();
            }
        });
        // TODO: back to main activity (if user clicks "CANCEL").
        cancelButton = (Button) findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void saveNewWord() {
        Storage.getInstance().saveWord(
                new Word(word.getText().toString(), meaning.getText().toString())
        );
    }

}
