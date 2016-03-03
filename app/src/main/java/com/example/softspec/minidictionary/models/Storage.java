package com.example.softspec.minidictionary.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.softspec.minidictionary.activities.WordActivity;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth, Boss, Nott on 29/2/2559.
 */
public class Storage {


    private List<Word> savedWords;
    private String DB = "WORDS";
    private SharedPreferences.Editor editor;
    private static Storage instance;

    private Storage(){
        savedWords = new ArrayList<Word>();
    }

    public static Storage getInstance() {
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }

    public void saveWord(Context context, Word word) {
        savedWords.add(word);
        saveStorage(context);
    }

    public List<Word> loadWords(Context context) {
        return savedWords;
    }

    public void clearWords(Context context) {
        savedWords.clear();
        saveStorage(context);
    }

    public void deleteWords(Context context, Word word) {
        savedWords.remove(word);
        saveStorage(context);
    }

    public String getTitleList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < savedWords.size(); i++) {
            sb.append(savedWords.get(i).getTitle()).append(",");
        }

        return sb.toString();
    }

    public String getMeaningList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < savedWords.size(); i++) {
            sb.append(savedWords.get(i).getMeaning()).append("_");
        }
        return sb.toString();
    }

    private void saveStorage(Context context) {
        editor = context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        editor.putInt("words_size", savedWords.size());
        for (int i = 0; i < savedWords.size(); i++) {
            editor.putString("title_" + i, savedWords.get(i).getTitle());
            editor.putString("meaning_" + i, savedWords.get(i).getMeaning());
        }
        editor.commit();
    }
}
