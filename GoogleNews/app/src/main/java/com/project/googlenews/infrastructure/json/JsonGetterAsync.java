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
import java.nio.charset.Charset;

public class JsonGetterAsync  extends AsyncTask <String, Void, JSONObject> {

    @Override
    protected void onPreExecute() {
        // Do stuff before the operation
    }

    @Override
    protected JSONObject doInBackground(String... params){
        try {
            JSONObject object = getJSONFromUrl();
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getJSONFromUrl() throws IOException, JSONException {
        URL url = new URL("https://ajax.googleapis.com/ajax/services/search/news?" +
                "v=1.0&q=barack%20obama&userip=INSERT-USER-IP");
        //URLConnection connection = url.openConnection();
        //connection.addRequestProperty("Referer", /* Enter the URL of your site here */);

        String line;
        StringBuilder builder = new StringBuilder();
        InputStream is =url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        JSONObject json = new JSONObject(builder.toString());
        return json;
    }


}