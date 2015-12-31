package com.project.googlenews.infrastructure.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.project.googlenews.R;
import com.project.googlenews.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item>{
    public ItemAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item current = getItem(position);
        return  current.getView();

    }
}
