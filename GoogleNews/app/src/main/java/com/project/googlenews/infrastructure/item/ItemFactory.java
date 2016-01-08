package com.project.googlenews.infrastructure.item;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.project.googlenews.infrastructure.url.BitmapDecoder;
import com.project.googlenews.model.Item;
import com.project.googlenews.model.listener.IFavoriteListener;
import com.project.googlenews.model.listener.IGoToListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemFactory {

    private final Context context;
    private final IGoToListener listener;
    private final IFavoriteListener favoriteListener;

    public ItemFactory(Context context, IGoToListener listener,IFavoriteListener favoriteListener) {
        this.context = context;
        this.listener = listener;
        this.favoriteListener = favoriteListener;
    }

    public  List<Item> createItem(JSONObject source) {
        List<Item> list = new ArrayList<>();
        try {
            JSONArray array = source.getJSONObject("responseData").getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Bitmap bitmap;
                if (object.has("image"))
                    bitmap = parseBitmap(object.getJSONObject("image").getString("url"));
                else bitmap = null;
                String title = object.getString("title");
                String content = object.getString("content");
                String publisher = object.getString("publisher");
                String date = object.getString("publishedDate");
                String sourceUrl = object.getString("unescapedUrl");
                list.add(new Item(context, bitmap, title, date, publisher, content,sourceUrl,listener,favoriteListener));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public float spToPixels(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return sp*scaledDensity;
    }

    private  Bitmap parseBitmap(String url) throws IOException, ExecutionException, InterruptedException {
        BitmapDecoder decoder = new BitmapDecoder((int) spToPixels(context,80));
        decoder.execute(url);
        Bitmap bitmap = decoder.get();
        if(bitmap != null)
            Log.i("w + h",bitmap.getWidth() + " + " +bitmap.getHeight());
        return bitmap;
    }
}
