package com.example.softspec.minidictionary.views;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.softspec.minidictionary.R;
import com.example.softspec.minidictionary.models.Word;

import java.util.List;

/**
 * Created by Earth on 31/3/2559.
 */
public class SynonymAdapter extends ArrayAdapter<String> {

    public SynonymAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.synonym_cell, null);
        }

        if(v != null){
            TextView words = (TextView)v.findViewById(R.id.tv_synonym);

            String syn = getItem(position);
            words.setTextColor(Color.WHITE);
            words.setText(syn);
        }

        return v;
    }
}
