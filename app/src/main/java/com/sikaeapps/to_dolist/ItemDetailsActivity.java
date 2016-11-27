package com.sikaeapps.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsActivity extends AppCompatActivity {

    static final int EDIT_ITEM_REQUEST_CODE = 3;

    @BindView(R.id.item_title_details)
    TextView title;

    @BindView(R.id.item_description_details)
    TextView description;

    @BindView(R.id.item_location_details)
    TextView location;

    private Item item;
    private ItemManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        manager = (ItemManager) intent.getSerializableExtra(Constants.MANAGER);
        item = (Item) intent.getSerializableExtra(Constants.ITEM);
        title.setText(item.getTitle());
        description.setText(item.getDescription());
        location.setText(item.getLocation());
    }

    public void editItem(View view) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra(Constants.MANAGER, manager);
        intent.putExtra(Constants.ITEM, item);
        startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_ITEM_REQUEST_CODE) {
            Intent intent = new Intent();
            intent.putExtra(Constants.MANAGER, data.getSerializableExtra(Constants.MANAGER));
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
