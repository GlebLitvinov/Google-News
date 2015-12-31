package com.project.googlenews.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.googlenews.R;

public class Item extends View {

    private ImageView image;
    private TextView title;
    private TextView date;
    private TextView source;
    private TextView content;

    public View getView() {
        return myView;
    }

    View myView;

    public Item(Context context) {
        super(context);
        init();
    }

    public Item(Context context, Bitmap image, String title, String date, String source, String content) {
        super(context);
        LayoutInflater inflater;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = inflater.inflate(R.layout.item, null);
        init();
        if(image != null)
         this.image.setImageBitmap(image);
        this.title.setText(Html.fromHtml(title));
        this.date.setText(date);
        this.source.setText(source);
        this.content.setText(Html.fromHtml(content));
    }

    private void init() {
        image = (ImageView) myView.findViewById(R.id.itemImageID);
        title = (TextView) myView.findViewById(R.id.titleID);
        date = (TextView) myView.findViewById(R.id.dateID);
        source = (TextView) myView.findViewById(R.id.sourceID);
        content = (TextView) myView.findViewById(R.id.textID);
    }
    
   

}
