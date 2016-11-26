package com.sikaeapps.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListActivity extends AppCompatActivity {

    @BindView(R.id.to_do_list_view)
    ListView toDoListView;
    @BindView(R.id.done_list_view)
    ListView doneListView;

    private ItemManager manager;

    public ItemManager getManager() {
        return manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        ButterKnife.bind(this);
        manager = new ItemManager();

        if (manager.getToDoItems().isEmpty() && manager.getDoneItems().isEmpty()) {
            try {
                manager.addItem(new Item(
                        "TODO Title Sample",
                        "TODO Description Sample",
                        "TODO Location Sample"));

                manager.addItem(new Item(
                        "DONE Title Sample",
                        "DONE Description Sample",
                        "DONE Location Sample"));

                manager.checkItemAtIndex(1);

            } catch (DuplicatedItemException e) {

            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toDoListView.setAdapter(new ItemAdapter(this, manager.getToDoItems()));
        doneListView.setAdapter(new ItemAdapter(this, manager.getDoneItems()));

        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ToDoListActivity.this, ItemDetailsActivity.class);
                Item item = manager.getItemAtIndex(position);
                Log.d("item_title", item.getTitle());
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
