package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_ITEM_REQUEST = 0;
    public static String STORE_NAME = "Unnamed";

    private ViewModel viewModel;
    private ItemAdapter itemAdapter;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView listLabelView = findViewById(R.id.list_label);
        listLabelView.setText(STORE_NAME);

        ImageView addItemBtn = findViewById(R.id.btn_add_item);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemEdit.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST);
            }
        });

        viewModel = new ViewModel(getApplication());
        viewModel.getStore(STORE_NAME).observe(this, new Observer<Store>() {
            @Override
            public void onChanged(Store store) {
                STORE_NAME = store.getStoreName();
            }
        });

        try {
            RecyclerView itemListView = findViewById(R.id.item_list);
            itemListView.setLayoutManager(new LinearLayoutManager(this));

            itemAdapter = new ItemAdapter(this, viewModel);
            itemAdapter.setItemList((ArrayList<Item>) viewModel.getAllItems(STORE_NAME));
            itemListView.setAdapter(itemAdapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Example of a call to a native method
        //tv.setText(stringFromJNI());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_actions, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem selectedItem) {

        switch(selectedItem.getItemId()) {
            case R.id.option_open_list:
                break;
            case R.id.option_rename_list:
                break;
            case R.id.option_delete_list:
                break;
        }

        return super.onOptionsItemSelected(selectedItem);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
                String name = data.getStringExtra(ItemAdapter.EXTRA_ITEM_NAME);
                double price = data.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0);
                int isBought = data.getIntExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, 0);
                Item item = new Item(0, name, price, isBought, itemAdapter.getHighestOrder(), STORE_NAME);

                if(itemAdapter.add(item))
                    Text.printMessage(this, name + " has been added");
                else
                    Text.printMessage(this, name + " could not be added");
            } else if(requestCode == ItemAdapter.EDIT_ITEM_REQUEST && resultCode == RESULT_OK) {
                long id = data.getLongExtra(ItemAdapter.EXTRA_ITEM_ID, 0);
                String name = data.getStringExtra(ItemAdapter.EXTRA_ITEM_NAME);
                double price = data.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0);
                int isBought = data.getIntExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, 0);
                Item item = new Item(id, name, price, isBought, itemAdapter.getHighestOrder(), STORE_NAME);

                if(itemAdapter.update(item, itemAdapter.getClickedMenuPos()))
                    Text.printMessage(this, "Changes to " + name + " has been saved");
                else
                    Text.printMessage(this, "Changes to " + name + " could not be saved");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
