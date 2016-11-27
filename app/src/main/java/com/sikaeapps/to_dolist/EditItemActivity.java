package com.sikaeapps.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditItemActivity extends AppCompatActivity {

    @BindView(R.id.new_edit_item_title)
    EditText titleEdit;

    @BindView(R.id.new_edit_item_description)
    EditText descriptionEdit;

    @BindView(R.id.new_edit_item_location)
    EditText locationEdit;

    private ItemManager manager;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_new_item);
        ButterKnife.bind(this);

        item = (Item) getIntent().getSerializableExtra("item");

        titleEdit.setText(item.getTitle());
        descriptionEdit.setText(item.getDescription());
        locationEdit.setText(item.getLocation());
    }

    public void saveNewItem(View view) {
        ItemManager manager = (ItemManager) getIntent().getSerializableExtra(Constants.MANAGER);

        Item updatedItem = new Item(
                titleEdit.getText().toString(),
                descriptionEdit.getText().toString(),
                locationEdit.getText().toString());

        int index = manager.getToDoItems().indexOf(item);

        try {
            manager.updateItemAt(index, updatedItem);
            Intent intent = new Intent();
            intent.putExtra(Constants.MANAGER, manager);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } catch (DuplicatedItemException e) {
            Toast.makeText(this, R.string.duplicated_item_message, Toast.LENGTH_LONG).show();
        }
    }

}
