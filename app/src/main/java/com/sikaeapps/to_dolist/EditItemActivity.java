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

    @BindView(R.id.new_item_title)
    EditText titleEdit;

    @BindView(R.id.new_item_description)
    EditText descriptionEdit;

    @BindView(R.id.new_item_location)
    EditText locationEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_new_item);
        ButterKnife.bind(this);

        Item item = (Item) getIntent().getSerializableExtra("item");

        titleEdit.setText(item.getTitle());
        descriptionEdit.setText(item.getDescription());
        locationEdit.setText(item.getLocation());
    }
}
