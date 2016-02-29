package com.example.softspec.minidictionary.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Word;

import java.util.List;

/**
 * Created by Earth, Boss, Nott on 29/2/2559.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, int resource, List<Word> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.word_cell, null);
        }

        Word word = getItem(position);

        if(word != null) {

            // TODO: set text to each cell.
        }

        return v;
    }
}

