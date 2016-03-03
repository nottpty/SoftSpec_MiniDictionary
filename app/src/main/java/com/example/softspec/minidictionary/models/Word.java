package com.example.softspec.minidictionary.models;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Earth, Boss, Nott on 29/2/2559.
 */
public class Word implements Serializable {

    private String title;
    private String meaning;

    public Word(String title, String meaning){
        this.title = title;
        this.meaning = meaning;
    }

    public String getTitle(){return title;}

    public String getMeaning() {return meaning;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return title.equals(word.title);

    }

    public static class AlphabetComparator implements Comparator<Word>{
        public int compare(Word word1,Word word2){
            return word1.getTitle().compareTo(word2.getTitle());
        }
    }

    // code goes here...
}
