package com.example.softspec.minidictionary.models;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Earth, Boss, Nott on 29/2/2559.
 */
public class Word implements Serializable{

    private String word;
    private String meaning;

    public Word(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    public String getTitle(){return word;}

    public String getMeaning() {return meaning;}

    public static class AlphabetComparator implements Comparator<Word>{
        public int compare(Word word1,Word word2){
            return word1.getTitle().compareTo(word2.getTitle());
        }
    }

    // code goes here...
}
