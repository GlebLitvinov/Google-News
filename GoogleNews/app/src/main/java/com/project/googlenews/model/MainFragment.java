package com.project.googlenews.model;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.googlenews.R;
import com.project.googlenews.infrastructure.item.ItemAdapter;
import com.project.googlenews.infrastructure.item.ItemFactory;
import com.project.googlenews.infrastructure.json.JsonGetterAsync;
import com.project.googlenews.model.listener.IFavoriteListener;
import com.project.googlenews.model.listener.IGoToListener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment implements ItemFragment {


    private ListView listView;
    TextView text;
    private ItemFactory factory;
    private String request;
    private ItemAdapter adapter;
    private Button btnLoadMore;
    private Spinner spinner;
    private Button searchButton;
    private int start = 0;
    private List<Item> list;

    private IGoToListener listener;
    IFavoriteListener favoriteListener;

    public MainFragment() {
        super();
    }

    public void setData(IGoToListener listener, IFavoriteListener favoriteListener) {
        this.listener = listener;
        this.favoriteListener = favoriteListener;
    }


    public void setData(List<Item> list) {
        this.list = list;
    }

    public List<Item> getItems() {
        return adapter.getItems();
    }

    private void fillListView() {
        for (; start < Math.min(list.size(), start + 8); start++) {
            adapter.add(list.get(start));

        }
    }


    private void fillListView(String request, ItemFactory factory) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container,
                false);
        if (factory != null) return view;
        factory = new ItemFactory(inflater.getContext(), listener, favoriteListener);
        searchButton = (Button) view.findViewById(R.id.search_button);
        text = (EditText) view.findViewById(R.id.search_request);
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

                fillListView(current, factory);

            }
        });
        spinner = (Spinner) view.findViewById(R.id.spinner);
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


        listView = (ListView) view.findViewById(R.id.list_view_main);
        // LoadMore button

        btnLoadMore = new Button(inflater.getContext());
        btnLoadMore.setText("Load More");

        list = new LinkedList<>();
        listView.addFooterView(btnLoadMore);
        adapter = new ItemAdapter(inflater.getContext(), R.layout.item);
        listView.setAdapter(adapter);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = SearchField.convertText(request, adapter.getCount(), spinner.getSelectedItem() == 1);

                Log.i("request", current);
                fillListView(current, factory);
                if (adapter.getCount() > 55) {
                    btnLoadMore.setVisibility(View.INVISIBLE);
                    btnLoadMore.setClickable(false);
                }
            }
        });
        // Adding Load More button to lisview at bottom


        // присваиваем адаптер списку

        searchButton.callOnClick();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
