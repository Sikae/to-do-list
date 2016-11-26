package com.sikaeapps.to_dolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter{

    private Context context;
    private List<Item> items;
    private static LayoutInflater inflater;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_layout, null);
        }
        TextView title = (TextView) view.findViewById(R.id.item_title);
        title.setText(items.get(i).getTitle());

        TextView location = (TextView) view.findViewById(R.id.item_location);
        location.setText(items.get(i).getLocation());

        return view;
    }
}
