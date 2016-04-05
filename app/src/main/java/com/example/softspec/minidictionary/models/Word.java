package com.example.softspec.minidictionary.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Earth, Boss, Nott on 29/2/2559.
 */
public class Word implements Serializable {

    private String title;
    private String meaning;
    private List<Word> synonym;

    public Word(String title, String meaning){
        this.title = title;
        this.meaning = meaning;
        synonym = new ArrayList<Word>();
    }

    public void setMeaning(String newMeaning) {
        this.meaning = newMeaning;
    }

    public String getTitle(){
        return title;
    }

    public String getMeaning() {
        return meaning;
    }

    public List<Word> getSynonym() {
        return synonym;
    }

    public void addSynonym(Word word) {
        synonym.add(word);
    }

    public void removeSynonym(Word word) {
        synonym.remove(word);
    }

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
