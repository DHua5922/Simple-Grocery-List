package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ItemEdit extends AppCompatActivity {

    private static int REQUEST = -1;
    private EditText nameInput;
    private EditText priceInput;
    private CheckBox isBoughtCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        nameInput = findViewById(R.id.name_input);
        priceInput = findViewById(R.id.price_input);
        isBoughtCheckBox = findViewById(R.id.checkbox_isBought);

        Intent request = getIntent();
        if(request.hasExtra(ItemAdapter.EXTRA_ITEM_ID)) {
            REQUEST = ItemAdapter.EDIT_ITEM_REQUEST;
            nameInput.setText(request.getStringExtra(ItemAdapter.EXTRA_ITEM_NAME));
            priceInput.setText(String.valueOf(request.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0)));
            isBoughtCheckBox.setChecked(request.getBooleanExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, false));
        } else {
            REQUEST = MainActivity.ADD_ITEM_REQUEST;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView labelActionView = findViewById(R.id.label_action);
        labelActionView.setText(request.getStringExtra(ItemAdapter.EXTRA_ACTION));

        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ItemEdit.this, MainActivity.class);
                startActivity(back);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        if(REQUEST == MainActivity.ADD_ITEM_REQUEST) {
            menuInflater.inflate(R.menu.menu_add_item, menu);
        }
        else {
            menuInflater.inflate(R.menu.menu_edit_item, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem selectedItem) {

        try {
            if (selectedItem.getItemId() == R.id.btn_add_item ||
                    selectedItem.getItemId() == R.id.overflow_save_changes) {
                finishItem();
            }
            return true;
        } catch (IllegalArgumentException e) {
            Text.printMessage(this, "Invalid price");
        }

        return super.onOptionsItemSelected(selectedItem);
    }

    private void finishItem() {
        String name = nameInput.getText().toString();
        if(name.isEmpty()) {
            Text.printMessage(this, "Name cannot be empty");
        }
        else {
            double price = Double.parseDouble(priceInput.getText().toString());
            final int IS_BOUGHT = 1;
            final int NOT_BOUGHT = 0;
            int isBought = isBoughtCheckBox.isChecked() ? IS_BOUGHT : NOT_BOUGHT;

            Intent data = new Intent();
            data.putExtra(ItemAdapter.EXTRA_ITEM_NAME, name);
            data.putExtra(ItemAdapter.EXTRA_ITEM_PRICE, price);
            data.putExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, isBought);
            long id = getIntent().getLongExtra(ItemAdapter.EXTRA_ITEM_ID, -1);
            if(id != -1) {
                data.putExtra(ItemAdapter.EXTRA_ITEM_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();
        }
    }
}
