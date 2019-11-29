package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.ads.AdView;

public class ItemEdit extends AppCompatActivity {

    private EditText nameInput;
    private EditText priceInput;
    private CheckBox isBoughtCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        // display banner ad
        Ads.displayBannerAd((AdView) findViewById(R.id.banner_ad));

        Intent request = getIntent();

        // fill in input fields and checkbox with item content
        nameInput = findViewById(R.id.name_input);
        priceInput = findViewById(R.id.price_input);
        isBoughtCheckBox = findViewById(R.id.checkbox_isBought);
        if(request.hasExtra(ItemAdapter.EXTRA_ITEM_ID)) {
            nameInput.setText(request.getStringExtra(ItemAdapter.EXTRA_ITEM_NAME));
            priceInput.setText(String.valueOf(request.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0)));
            isBoughtCheckBox.setChecked(request.getIntExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, 0) == 1);
        }

        // format action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // set toolbar title
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(request.getStringExtra(MainActivity.EXTRA_ACTION));
        // set back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem selectedItem) {

        try {
            if (selectedItem.getItemId() == R.id.overflow_save_changes) {
                finishItem();
            }
            else if(selectedItem.getItemId() == android.R.id.home) {
                // go back to previous page without changing this item
                Intent request = getIntent();
                setResult(RESULT_CANCELED, request);
                finish();
            }
            return true;
        } catch (IllegalArgumentException e) {
            Text.printMessage(this, "Invalid price", Text.FAIL);
        }

        return super.onOptionsItemSelected(selectedItem);
    }

    private void finishItem() {
        String name = nameInput.getText().toString();
        if(name.isEmpty()) {
            Text.printMessage(this, "Name cannot be empty", Text.FAIL);
        }
        else {
            // send updates to this item back to previous page
            double price = Double.parseDouble(priceInput.getText().toString());
            final int IS_BOUGHT = 1;
            final int NOT_BOUGHT = 0;
            int isBought = isBoughtCheckBox.isChecked() ? IS_BOUGHT : NOT_BOUGHT;

            Intent data = new Intent();
            data.putExtra(ItemAdapter.EXTRA_ITEM_NAME, name);
            data.putExtra(ItemAdapter.EXTRA_ITEM_PRICE, price);
            data.putExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, isBought);
            long id = getIntent().getLongExtra(ItemAdapter.EXTRA_ITEM_ID, 0);
            if(id > 0) {
                data.putExtra(ItemAdapter.EXTRA_ITEM_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();
        }
    }
}
