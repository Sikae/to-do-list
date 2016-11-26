package com.sikaeapps.to_dolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsActivity extends AppCompatActivity {

    @BindView(R.id.item_title_details)
    TextView title;

    @BindView(R.id.item_description_details)
    TextView description;

    @BindView(R.id.item_location_details)
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ButterKnife.bind(this);

        Item item = (Item) getIntent().getSerializableExtra("item");
        title.setText(item.getTitle());
        description.setText(item.getDescription());
        location.setText(item.getLocation());
    }
}
