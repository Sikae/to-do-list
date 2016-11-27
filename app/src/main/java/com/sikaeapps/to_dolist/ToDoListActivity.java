package com.sikaeapps.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 2;
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

        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ToDoListActivity.this, ItemDetailsActivity.class);
                Item item = manager.getItemAtIndex(position);
                intent.putExtra(Constants.ITEM, item);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNewItemActivity.class);
                intent.putExtra(Constants.MANAGER, manager);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void updateListViews() {
        toDoListView.setAdapter(new ItemAdapter(this, manager.getToDoItems()));
        doneListView.setAdapter(new ItemAdapter(this, manager.getDoneItems()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            manager = (ItemManager) data.getSerializableExtra(Constants.MANAGER);
        }
    }
}
