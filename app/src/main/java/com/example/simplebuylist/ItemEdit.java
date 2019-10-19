package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

public class ItemEdit extends AppCompatActivity {

    private static int REQUEST = -1;
    public static final String EXTRA_NAME = "package com.example.simplebuylist.EXTRA_NAME";
    public static final String EXTRA_PRICE = "package com.example.simplebuylist.EXTRA_PRICE";
    public static final String EXTRA_ISBOUGHT = "package com.example.simplebuylist.EXTRA_ISBOUGHT";
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Intent request = new Intent();
        REQUEST = request.getIntExtra(MainActivity.EXTRA_ACTION_REQUEST, 0);
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
            if (selectedItem.getItemId() == R.id.btn_add_item) {
                addNote();
                return true;
            }
        } catch (IllegalArgumentException e) {
            Text.printMessage(this, "Invalid price");
        }

        return super.onOptionsItemSelected(selectedItem);
    }

    private void addNote() {
        EditText nameInput = findViewById(R.id.name_input);
        EditText priceInput = findViewById(R.id.price_input);
        CheckBox isBoughtCheckBox = findViewById(R.id.checkbox_isBought);

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
            data.putExtra(EXTRA_NAME, name);
            data.putExtra(EXTRA_PRICE, price);
            data.putExtra(EXTRA_ISBOUGHT, isBought);

            setResult(RESULT_OK, data);
            finish();
        }
    }
}
