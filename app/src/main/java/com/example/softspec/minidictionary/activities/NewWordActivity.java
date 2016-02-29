package com.example.softspec.minidictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.softspec.minidictionary.R;

public class NewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        initComponents();
    }

    private void initComponents() {
        // TODO: save the new word into storage (if user clicks "SAVE").
        // TODO: back to main activity (if user clicks "CANCEL").
    }
}
