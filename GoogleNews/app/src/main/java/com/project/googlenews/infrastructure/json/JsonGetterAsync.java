package com.project.googlenews.infrastructure.json;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JsonGetterAsync  extends AsyncTask <String, Void, JSONObject> {


    @Override

    protected void onPreExecute() {
        // Do stuff before the operation
    }

    @Override
    protected JSONObject doInBackground(String... params){
        try {
            JSONObject object = getJSONFromUrl(params[0]);
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getJSONFromUrl(String urlString) throws IOException, JSONException {

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        String line;
        StringBuilder builder = new StringBuilder();
        InputStream is =connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }


        JSONObject json = new JSONObject(builder.toString());
        Log.i("json",json.toString());
        return json;
    }


}