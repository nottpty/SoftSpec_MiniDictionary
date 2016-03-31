package com.example.softspec.minidictionary.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Storage;
import com.example.softspec.minidictionary.models.Word;

/**
 * Created by Earth on 31/3/2559.
 */
public class AddSynonymActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button addButton;
    private EditText synonym;
    private Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_synonym);
        initComponents();
    }

    private void initComponents() {
        word = (Word) getIntent().getSerializableExtra("word");
        synonym = (EditText) findViewById(R.id.edtxt_addSynonym);
        addButton = (Button) findViewById(R.id.btn_addSynonym);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewSynonym(synonym.getText().toString());
                Toast.makeText(AddSynonymActivity.this, "Adding Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddSynonymActivity.this, WordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancelButton = (Button) findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddSynonymActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
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
            }
        });

    }

    private void saveNewSynonym(String synonym) {
        Word synWord = Storage.getInstance().getWordByTitle(synonym);
        Storage.getInstance().addSynonym(this, word, synWord);
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
