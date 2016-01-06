package com.project.googlenews.infrastructure.url;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapDecoder extends AsyncTask<String,Void,Bitmap> {


    private int height;

    public BitmapDecoder(int height) {
        this.height = height;
    }

    public  Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            BitmapFactory.Options options = decodeSampledBitmapFromResource(input, height);
            input.close();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            Bitmap myBitmap =  BitmapFactory.decodeStream(input, null, options);
            input.close();
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options,  int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        int inSampleSize = 1;

        if (height > reqHeight ) {

            final int halfHeight = height / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight                    ) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static BitmapFactory.Options decodeSampledBitmapFromResource(InputStream stream, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream,null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,  reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return options;

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return getBitmapFromURL(params[0]);
    }
}
