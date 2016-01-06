package com.project.googlenews.model;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.project.googlenews.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchField extends Fragment {

    private EditText text;
    private Button searchButton;
    private Context context;
    View myView;

    private static final String patternByRelevance = "https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=%s&rsz=8&start=%s&userip=192.168.0.1";
    private static final String patternByDate = "https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=%s&rsz=8&scoring=d&start=%s&userip=192.168.0.1";







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        init();
        myView = inflater.inflate(R.layout.search_field, container, false);
        return myView;
    }

    private void init() {
        searchButton = (Button) myView.findViewById(R.id.search_button);
        text = (EditText) myView.findViewById(R.id.search_request);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String request = text.getText().toString();
                request = convertText(request,0,false);

                Log.i("request", request);

            }
        });

    }


    public static String convertText(String source,int start,boolean byDate) {
        String s = null;
        try {
            s = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(source.length() == 0) s = "%7F";
        Log.i("convert", s);
        String pattern;
        if(byDate == false){
            pattern = patternByRelevance;
        } else{
            pattern = patternByDate;
        }

        s = String.format(pattern, s,start);
        return s;
    }
}
