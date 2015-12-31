package com.project.googlenews.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.project.googlenews.R;
import com.project.googlenews.infrastructure.item.ItemAdapter;
import com.project.googlenews.infrastructure.item.ItemFactory;
import com.project.googlenews.infrastructure.json.JsonGetterAsync;
import com.project.googlenews.model.Item;
import com.project.googlenews.model.SearchField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    private ListView listView;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view_main);

        // присваиваем адаптер списку
        Button searchButton = (Button) findViewById(R.id.search_button);
        text = (EditText) findViewById(R.id.search_request);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String request = text.getText().toString();
                request = SearchField.convertText(request);
                Log.i("request", request);
                JsonGetterAsync getterAsync = new JsonGetterAsync();
                getterAsync.execute(request);
                try {
                    List<Item> list = ItemFactory.createItem(getterAsync.get(), getApplicationContext());
                    ItemAdapter adapter = new ItemAdapter(getApplicationContext(),R.layout.item,list);
                    listView.setAdapter(adapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void saveBitmap(String filename, Bitmap bmp) {
        FileOutputStream out = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Pictures" + filename);
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
        } catch (Exception e) {
            Log.e("SaveBitmap", "FileOutStream failed");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
