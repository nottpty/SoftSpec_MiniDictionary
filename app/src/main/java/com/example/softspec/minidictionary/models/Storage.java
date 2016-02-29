package com.example.softspec.minidictionary.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth, Boss, Nott on 29/2/2559.
 */
public class Storage {


    private List<Word> savedWords;

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

    public void saveWord(Word word) {
        savedWords.add(word);
    }

    public List<Word> loadWords() {
        return savedWords;
    }

    public void clearWords() {
        savedWords.clear();
    }

    public void deleteWords(Word word) {
        savedWords.remove(word);
    }
}
