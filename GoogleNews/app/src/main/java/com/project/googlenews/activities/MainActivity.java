package com.project.googlenews.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.googlenews.R;
import com.project.googlenews.infrastructure.item.ItemAdapter;
import com.project.googlenews.infrastructure.item.ItemFactory;
import com.project.googlenews.infrastructure.json.JsonGetterAsync;
import com.project.googlenews.model.Item;
import com.project.googlenews.model.SearchField;
import com.project.googlenews.model.listener.IGoToListener;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity implements IGoToListener {

    private ListView listView;
    TextView text;
    List<Item> list;
    private ItemFactory factory;
    private String request;
    private ItemAdapter adapter;
    private Button btnLoadMore;
    private Spinner spinner;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        factory = new ItemFactory(this, this);
        searchButton = (Button) findViewById(R.id.search_button);
        text = (EditText) findViewById(R.id.search_request);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                listView.smoothScrollToPosition(0);
                btnLoadMore.setVisibility(View.VISIBLE);
                btnLoadMore.setClickable(true);
                request = text.getText().toString();
                String current = SearchField.convertText(request, 0, spinner.getSelectedItemId() == 1);
                Log.i("request", current);

                fillListView(current);

            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
               searchButton.callOnClick();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        listView = (ListView) findViewById(R.id.list_view_main);
        // LoadMore button
        btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        list = new LinkedList<>();
        listView.addFooterView(btnLoadMore);
        adapter = new ItemAdapter(this, R.layout.item);
        listView.setAdapter(adapter);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = SearchField.convertText(request, adapter.getCount(), spinner.getSelectedItem() == 1);

                Log.i("request", current);
                fillListView(current);
                if (adapter.getCount() > 55) {
                    btnLoadMore.setVisibility(View.INVISIBLE);
                    btnLoadMore.setClickable(false);
                }
            }
        });
        // Adding Load More button to lisview at bottom


        // присваиваем адаптер списку

        searchButton.callOnClick();
    }


    private void fillListView(String request) {
        JsonGetterAsync getterAsync = new JsonGetterAsync();
        getterAsync.execute(request);
        try {
            list = (factory.createItem(getterAsync.get()));
            adapter.addAll(list);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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

    @Override
    public void goTo(String url) {
        Intent browserView = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserView);
    }


}
