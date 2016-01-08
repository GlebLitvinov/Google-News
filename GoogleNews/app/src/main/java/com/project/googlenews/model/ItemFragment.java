package com.project.googlenews.model;

import android.app.Fragment;
import android.widget.ListView;

import com.project.googlenews.infrastructure.item.ItemAdapter;
import com.project.googlenews.infrastructure.item.ItemFactory;
import com.project.googlenews.infrastructure.json.JsonGetterAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ItemFragment {


    void setData(List<Item> list);

    List<Item> getItems();


}
