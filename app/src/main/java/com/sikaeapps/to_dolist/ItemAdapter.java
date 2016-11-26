package com.sikaeapps.to_dolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends BaseAdapter{

    private List<Item> items;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, List<Item> items) {
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
        ItemViewHolder holder;

        if (view != null) {
            holder = (ItemViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_layout, viewGroup, false);
            holder = new ItemViewHolder(view);
            view.setTag(holder);
        }

        holder.titleTextView.setText(items.get(i).getTitle());
        holder.locationTextView.setText(items.get(i).getLocation());

        return view;
    }

    static class ItemViewHolder {
        @BindView(R.id.item_title) TextView titleTextView;
        @BindView(R.id.item_location) TextView locationTextView;

        public ItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
