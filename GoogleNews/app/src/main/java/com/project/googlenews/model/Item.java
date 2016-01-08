package com.project.googlenews.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.googlenews.R;
import com.project.googlenews.model.listener.IFavoriteListener;
import com.project.googlenews.model.listener.IGoToListener;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;

public class Item extends View implements Serializable {

    private ImageView image;
    private TextView title;
    private TextView date;
    private TextView source;
    private TextView content;
    private String sourceUrl;
    private IGoToListener listener;
    private IFavoriteListener favoriteListener;
    private boolean favorite;

    public View getView() {
        return myView;
    }

    View myView;

    public Item(Context context) {
        super(context);
        init();
    }


   // public Item(Context context, Bitmap image, String title, String date, String source, String content, String sourceUrl, IGoToListener listener, boolean favorite) {
     //   this(context, image, title, date, source, content, sourceUrl, listener);
    //    this.favorite = favorite;
    //}

    public Item(Context context, Bitmap image, String title, String date, String source, String content, String sourceUrl, IGoToListener listener,IFavoriteListener favoriteListener) {
        super(context);
        LayoutInflater inflater;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = inflater.inflate(R.layout.item, null);
        init();
        if (image != null)
            this.image.setImageBitmap(image);
        else
            this.image.setMaxHeight(0);
        this.title.setText(Html.fromHtml(title));
        this.date.setText(date);
        this.source.setText(source);
        this.content.setText(Html.fromHtml(content));
        this.sourceUrl = sourceUrl;
        this.listener = listener;
        this.favorite = false;
        this.favoriteListener = favoriteListener;
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
        final ImageButton fav = (ImageButton) myView.findViewById(R.id.button_favorite);
        fav.setImageResource(R.drawable.unfavorite);
        fav.setTag(R.drawable.unfavorite);
        if(favorite == true){
            fav.setImageResource(R.drawable.favorite);
            fav.setTag(R.drawable.favorite);
        }
        fav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Integer.parseInt(fav.getTag().toString()) == R.drawable.favorite) {
                    fav.setImageResource(R.drawable.unfavorite);
                    fav.setTag(R.drawable.unfavorite);
                    if (favorite == false) {

                        favoriteListener.deleteFromFavorite(getThis());
                        return;
                    }
                } else {
                    fav.setImageResource(R.drawable.favorite);
                    fav.setTag(R.drawable.favorite);
                    if (favorite == false) {
                        favoriteListener.setFavorite(getThis());
                        return;
                    }
                }

            }
        });

    }

    private Item getThis(){
        return this;
    }

    public void goTo(View v) {
        Intent browserView = new Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl));
        getContext().startActivity(browserView);
    }

    @Override
    public String toString(){
        String string = new String();
        string = title.getText().toString()+"\n"+ date.getText().toString();
        return string;
    }

}
