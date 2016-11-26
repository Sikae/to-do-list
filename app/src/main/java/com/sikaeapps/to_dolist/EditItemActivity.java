package com.sikaeapps.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditItemActivity extends AppCompatActivity {

    @BindView(R.id.title_edit)
    EditText titleEdit;

    @BindView(R.id.description_edit)
    EditText descriptionEdit;

    @BindView(R.id.location_edit)
    EditText locationEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        ButterKnife.bind(this);

        Item item = (Item) getIntent().getSerializableExtra("item");

        titleEdit.setText(item.getTitle());
        descriptionEdit.setText(item.getDescription());
        locationEdit.setText(item.getLocation());
    }
}
