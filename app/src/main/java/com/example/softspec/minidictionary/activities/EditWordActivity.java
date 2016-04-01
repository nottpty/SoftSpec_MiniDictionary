package com.example.softspec.minidictionary.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;

public class EditWordActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button editButton;
    private TextView title;
    private EditText meaning;
    private Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);
        initComponents();
    }

    private void initComponents() {
        title = (TextView) findViewById(R.id.tv_title);
        meaning  = (EditText) findViewById(R.id.edtxt_meaning);
        word = (Word)getIntent().getSerializableExtra("word");
        title.setText(word.getTitle());
        meaning.setText(word.getMeaning());
        editButton = (Button) findViewById(R.id.btn_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditedWord();
                Toast.makeText(EditWordActivity.this, "Editing Successful", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        cancelButton = (Button) findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void saveEditedWord() {
        Storage.getInstance().editWord(this,
                word, meaning.getText().toString());
    }
}
