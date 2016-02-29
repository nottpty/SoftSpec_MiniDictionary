package com.example.softspec.minidictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.softspec.minidictionary.R;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        initComponents();
    }

    private void initComponents() {
        // TODO: show title and meaning.
    }
}
