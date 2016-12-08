package com.sikaeapps.to_dolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import java.util.List;

import static com.sikaeapps.to_dolist.ToDoListActivity.EDIT_ITEM_REQUEST_CODE;


public class ToDoItemAdapter extends ArrayAdapter {
    private final LayoutInflater inflater;
    private final ViewBinderHelper binderHelper;
    private List<Item> items;
    private AdapterView.OnItemClickListener onItemClickListener;
    private ItemManager manager;
    private Activity activity;

    public ToDoItemAdapter(Context context, List<Item> items, ItemManager manager, Activity activity) {
        super(context, R.layout.to_do_item_layout, items);
        this.activity = activity;
        this.manager = manager;
        this.items = items;
        inflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView locationTextView;
        View toDoItemListView;
        ImageButton deleteButton;
        ImageButton checkButton;
        SwipeRevealLayout swipeLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.to_do_item_layout, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.item_title);
            holder.locationTextView = (TextView) convertView.findViewById(R.id.item_location);
            holder.deleteButton = (ImageButton)convertView.findViewById(R.id.delete_button);
            holder.checkButton = (ImageButton)convertView.findViewById(R.id.check_button);
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout);
            holder.toDoItemListView = convertView.findViewById(R.id.to_do_item_list_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.toDoItemListView.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
            Log.d("MESSAGE", "ON CLICK IN ITEM LIST VIEW" + position);
            Intent intent = new Intent(getContext(), ItemDetailsActivity.class);
            Item item = manager.getItemAtIndex(position);
            intent.putExtra(Constants.MANAGER, manager);
            intent.putExtra(Constants.ITEM, item);
            activity.startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
            }
        });

        holder.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MESSAGE", "CHECK: " + position);
                manager.checkItemAtIndex(position);
                notifyDataSetChanged();
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.removeItemAt(position);
                notifyDataSetChanged();
            }
        });

        holder.titleTextView.setText(items.get(position).getTitle());
        holder.locationTextView.setText(items.get(position).getLocation());

        return convertView;
    }
}
