package com.example.softspec.minidictionary.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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
                if (isNotWord(word.getText().toString())) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(NewWordActivity.this);
                    dlgAlert.setMessage("Invalid word.");
                    dlgAlert.setTitle("Error");
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                } else {
                    saveNewWord();
                    finish();
                }

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

    private boolean isSpecial(String word) {
        char ch;
        for(int i=0 ; i<word.length() ; i++) {
            ch = word.charAt(i);
            if(!(Character.isSpace(ch) || Character.isLetter(ch))) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotWord(String word) {
        if(word.equals("") || (word.startsWith(" ")) || isSpecial(word)) {
            return true;
        }
        return false;
    }
}
