package com.project.googlenews.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.googlenews.R;
import com.project.googlenews.model.listener.IGoToListener;

public class Item extends View {

    private ImageView image;
    private TextView title;
    private TextView date;
    private TextView source;
    private TextView content;
    private String sourceUrl;
    private IGoToListener listener;

    public View getView() {
        return myView;
    }

    View myView;

    public Item(Context context) {
        super(context);
        init();
    }

    public Item(Context context, Bitmap image, String title, String date, String source, String content,String sourceUrl,IGoToListener listener) {
        super(context);
        LayoutInflater inflater;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = inflater.inflate(R.layout.item, null);
        init();
        if(image != null)
         this.image.setImageBitmap(image);
        else
            this.image.setMaxHeight(0);
        this.title.setText(Html.fromHtml(title));
        this.date.setText(date);
        this.source.setText(source);
        this.content.setText(Html.fromHtml(content));
        this.sourceUrl = sourceUrl;
        this.listener = listener;
    }

    private void init() {
        image = (ImageView) myView.findViewById(R.id.itemImageID);
        title = (TextView) myView.findViewById(R.id.titleID);
        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.goTo(sourceUrl);
            }
        });
        date = (TextView) myView.findViewById(R.id.dateID);
        source = (TextView) myView.findViewById(R.id.sourceID);
        content = (TextView) myView.findViewById(R.id.textID);
    }

    public void goTo(View v){
        Intent browserView = new Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl));
        getContext().startActivity(browserView);
    }
   

}
