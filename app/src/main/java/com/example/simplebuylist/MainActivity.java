package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_ITEM_REQUEST = 0;
    public static final int EDIT_ITEM_REQUEST = 1;
    public static final String EXTRA_ACTION_REQUEST = "com.example.simplebuylist.EXTRA_ACTION_REQUEST";
    private ViewModel viewModel;
    private ItemAdapter itemAdapter;
    public static String STORE_NAME = "Unnamed";

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
        getSupportActionBar().setTitle(STORE_NAME);

        ImageView addItemBtn = findViewById(R.id.btn_add_item);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemEdit.class);
                intent.putExtra(EXTRA_ACTION_REQUEST, ADD_ITEM_REQUEST);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String name = data.getStringExtra(ItemEdit.EXTRA_NAME);
        double price = data.getDoubleExtra(ItemEdit.EXTRA_PRICE, 0);
        int isBought = data.getIntExtra(ItemEdit.EXTRA_ISBOUGHT, 0);

        try {
            if(requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
                Item item = new Item(0, name, price, isBought, itemAdapter.getHighestOrder(), STORE_NAME);
                if(itemAdapter.add(item))
                    Text.printMessage(this, name + " has been added");
                else
                    Text.printMessage(this, name + " could not be added");
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
