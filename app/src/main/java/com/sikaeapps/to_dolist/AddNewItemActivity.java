package com.sikaeapps.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewItemActivity extends AppCompatActivity {

    @BindView(R.id.new_item_title)
    EditText title;

    @BindView(R.id.new_item_description)
    EditText description;

    @BindView(R.id.new_item_location)
    EditText location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_new_item);
        ButterKnife.bind(this);
    }

    public void saveNewItem(View view) {
        ItemManager manager = (ItemManager) getIntent().getSerializableExtra(Constants.MANAGER);

        Item newItem = new Item(title.getText().toString(),
                description.getText().toString(),
                location.getText().toString());

        try {
            manager.addItem(newItem);
            Intent intent = new Intent();
            intent.putExtra(Constants.MANAGER, manager);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } catch (DuplicatedItemException e) {
            Toast.makeText(this, R.string.duplicated_item_message, Toast.LENGTH_LONG).show();
        }
    }
}
