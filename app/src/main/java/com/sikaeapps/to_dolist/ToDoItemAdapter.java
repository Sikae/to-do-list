package com.sikaeapps.to_dolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class ToDoItemAdapter extends ArrayAdapter {

    private final LayoutInflater inflater;
    private final ViewBinderHelper binderHelper;
    private List<Item> items;

    public ToDoItemAdapter(Context context, List<Item> items) {
        super(context, R.layout.to_do_item_layout, items);
        this.items = items;
        inflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView locationTextView;
        View deleteView;
        SwipeRevealLayout swipeLayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.to_do_item_layout, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.item_title);
            holder.locationTextView = (TextView) convertView.findViewById(R.id.item_location);
            holder.deleteView = convertView.findViewById(R.id.delete_layout);
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        final String item = (String) getItem(position);
//        if (item != null) {
//            binderHelper.bind(holder.swipeLayout, item);
//
//            holder.titleTextView.setText(items.get(position).getTitle());
//            holder.locationTextView.setText(items.get(position).getLocation());
//            holder.deleteView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    remove(item);
//                }
//            });
//        }

        holder.titleTextView.setText(items.get(position).getTitle());
        holder.locationTextView.setText(items.get(position).getLocation());
//
        return convertView;
    }


}
