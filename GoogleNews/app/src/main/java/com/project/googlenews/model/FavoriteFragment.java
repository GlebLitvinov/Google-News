package com.project.googlenews.model;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.googlenews.R;
import com.project.googlenews.infrastructure.item.ItemAdapter;
import com.project.googlenews.infrastructure.item.ItemFactory;
import com.project.googlenews.infrastructure.json.JsonGetterAsync;
import com.project.googlenews.model.listener.IFavoriteListener;
import com.project.googlenews.model.listener.IGoToListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteFragment extends Fragment implements ItemFragment{

    private ListView listView;
    List<Item> list;
    private ItemAdapter adapter;
    private Button btnLoadMore;
    private int start;




    public void setData(List<Item> list){

        this.list = list;
    }

    public List<Item> getItems(){
        return adapter.getItems();
    }

    private void fillListView() {
        for(;start<Math.min(list.size(),start+8);start++){
            adapter.add(list.get(start));

        }

    }

    private void fillListView(String request,ItemFactory factory) {
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
        start = 0;
        if(list == null) list = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_favorite, container,
                false);
        listView = (ListView) view.findViewById(R.id.list_view_favorite);
        // LoadMore button

        btnLoadMore = new Button(inflater.getContext());
        btnLoadMore.setText("Load More");

        listView.addFooterView(btnLoadMore);
        adapter = new ItemAdapter(inflater.getContext(), R.layout.item);
        listView.setAdapter(adapter);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillListView();
                if (start == list.size()) {
                    btnLoadMore.setVisibility(View.INVISIBLE);
                    btnLoadMore.setClickable(false);
                }
            }
        });
        btnLoadMore.callOnClick();
        return view;
    }


    @Override
    public void onResume(){
        super.onResume();
        if(start == 0) btnLoadMore.callOnClick();
        if (start < list.size()) {
            btnLoadMore.setVisibility(View.VISIBLE);
            btnLoadMore.setClickable(true);
        }

    }

  


}
