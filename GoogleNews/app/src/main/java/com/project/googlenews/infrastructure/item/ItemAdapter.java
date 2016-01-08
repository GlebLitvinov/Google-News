package com.project.googlenews.infrastructure.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.project.googlenews.R;
import com.project.googlenews.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item>{
    private List<Item> items;
    public ItemAdapter(Context context, int resource) {
        super(context, resource);
        items = new ArrayList<>();
    }

    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.items = objects;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public void add(Item object) {
        super.add(object);
        items.add(object);
    }

    @Override
    public void addAll(Collection<? extends Item> collection) {
        super.addAll(collection);
        items.addAll(collection);
    }

    @Override
    public void remove(Item object) {
        super.remove(object);
        items.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
        items.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item current = getItem(position);
        return  current.getView();

    }
}
