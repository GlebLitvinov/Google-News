package com.project.googlenews.infrastructure.item;

import android.content.Context;
import android.graphics.Bitmap;

import com.project.googlenews.activities.MainActivity;
import com.project.googlenews.infrastructure.url.UrlDecoder;
import com.project.googlenews.model.Item;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemFactory {
    public static List<Item> createItem(JSONObject source, Context context) {
        List<Item> list = new ArrayList<Item>();
        try {
            JSONArray array = source.getJSONObject("responseData").getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Bitmap bitmap;
                if (object.has("image"))
                    bitmap = parseBitmap(object.getJSONObject("image").getString("url"));
                else bitmap = null;
                String title = URLDecoder.decode(object.getString("title"), "UTF-8");
              //  title = StringEscapeUtils.unescapeHtml(title);
                String content = URLDecoder.decode(object.getString("content"), "UTF-8");
                //content = StringEscapeUtils.unescapeHtml(content);
                String publisher = URLDecoder.decode(object.getString("publisher"), "UTF-8");
                String date = URLDecoder.decode(object.getString("publishedDate"), "UTF-8");
                list.add(new Item(context, bitmap, title, date, publisher, content));
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


    private static Bitmap parseBitmap(String url) throws IOException, ExecutionException, InterruptedException {
        UrlDecoder decoder = new UrlDecoder();
        decoder.execute(url);
        Bitmap bitmap = decoder.get();
        return bitmap;
    }
}
