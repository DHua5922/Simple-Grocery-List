package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
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

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static final int ADD_ITEM_REQUEST = 0;
    public static String STORE_NAME = "Unnamed";
    public static Store currentStore;

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

        ImageView addItemBtn = findViewById(R.id.btn_add_item);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemEdit.class);
                intent.putExtra(ItemAdapter.EXTRA_ACTION, "Add Item");
                startActivityForResult(intent, ADD_ITEM_REQUEST);
            }
        });

        final ViewModel viewModel = new ViewModel(getApplication());
        RecyclerView itemListView = findViewById(R.id.item_list);
        itemListView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(this, viewModel);
        itemListView.setAdapter(itemAdapter);

        final LifecycleOwner lifecycleOwner = this;
        viewModel.getAllStores().observe(lifecycleOwner, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> stores) {
                try {
                    if(stores.size() == 0) {
                        currentStore = new Store(0, "Unnamed", 0);
                        viewModel.insert(currentStore);
                    }
                    else {
                        currentStore = (currentStore != null) ? currentStore : stores.get(stores.size() - 1);
                    }

                    STORE_NAME = currentStore.getStoreName();
                    viewModel.getAllItems(STORE_NAME).observe(lifecycleOwner, new Observer<List<Item>>() {
                        @Override
                        public void onChanged(List<Item> items) {
                            try {
                                items = viewModel.getItemList(STORE_NAME);
                                itemAdapter.setItemList(items);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    TextView listLabelView = findViewById(R.id.list_label);
                    listLabelView.setText(STORE_NAME);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

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
        ViewModel viewModel = new ViewModel(getApplication());
        try {
            switch(selectedItem.getItemId()) {
                case R.id.option_new_list:
                    Dialog.createList(this, "Enter a name for the new list", viewModel);
                    break;
                case R.id.option_rename_list:
                    Dialog.renameList(this, "Enter a new name for this list", viewModel);
                    break;
                case R.id.option_open_list:
                    TextView listLabelView = findViewById(R.id.list_label);
                    Dialog.openList(this, "Choose which list to open", viewModel, itemAdapter, listLabelView);
                    break;
                case R.id.option_delete_list:
                    Dialog.confirmListDeletion(this, "Are you sure you want to delete this list?", viewModel);
                    break;
                case R.id.option_delete_all_lists:
                    Dialog.confirmAllListDeletion(this, "Are you sure you want to delete all your lists?", viewModel);
                    break;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(selectedItem);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(resultCode == RESULT_OK) {
                String name = data.getStringExtra(ItemAdapter.EXTRA_ITEM_NAME);
                Item item = new Item(
                        data.getLongExtra(ItemAdapter.EXTRA_ITEM_ID, 0),
                        name,
                        data.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0),
                        data.getIntExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, 0),
                        itemAdapter.getHighestOrder(),
                        STORE_NAME
                );

                if (requestCode == ADD_ITEM_REQUEST) {
                    if (itemAdapter.add(item)) {
                        Text.printMessage(this, name + " has been added");
                    }
                    else
                        Text.printMessage(this, name + " could not be added");
                } else if (requestCode == ItemAdapter.EDIT_ITEM_REQUEST) {
                    if (itemAdapter.update(item))
                        Text.printMessage(this, "Changes to " + name + " has been saved");
                    else
                        Text.printMessage(this, "Changes to " + name + " could not be saved");
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);

        popup.setOnMenuItemClickListener(this);
        switch(v.getId()) {
            case R.id.btn_sort_a_to_z:
                popup.inflate(R.menu.menu_sort_a_to_z);
                break;
            case R.id.btn_sort_price:
                popup.inflate(R.menu.menu_sort_price);
                break;
            case R.id.btn_delete_items:
                popup.inflate(R.menu.menu_delete_items);
                break;
            default:
                popup.inflate(R.menu.menu_search_items);
        }
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ViewModel viewModel = new ViewModel(getApplication());
        try {
            switch (item.getItemId()) {
                case R.id.menu_item_sort_a_to_z:
                    itemAdapter.setItemList(viewModel.sortItemsAToZ(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_z_to_a:
                    itemAdapter.setItemList(viewModel.sortItemsZToA(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_checked_a_to_z:
                    itemAdapter.setItemList(viewModel.sortCheckedItemsAToZ(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_checked_z_to_a:
                    itemAdapter.setItemList(viewModel.sortCheckedItemsZToA(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_unchecked_a_to_z:
                    itemAdapter.setItemList(viewModel.sortUncheckedItemsAToZ(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_unchecked_z_to_a:
                    itemAdapter.setItemList(viewModel.sortUncheckedItemsZToA(STORE_NAME));
                    return true;


                case R.id.menu_item_sort_price_increasing:
                    return true;
                case R.id.menu_item_sort_price_decreasing:
                    return true;
                case R.id.menu_item_sort_checked_price_increasing:
                    return true;
                case R.id.menu_item_sort_checked_price_decreasing:
                    return true;
                case R.id.menu_item_sort_unchecked_price_increasing:
                    return true;
                case R.id.menu_item_sort_unchecked_price_decreasing:
                    return true;



                case R.id.menu_item_delete_all:
                    return true;
                case R.id.menu_item_delete_checked:
                    return true;
                case R.id.menu_item_delete_unchecked:
                    return true;



                case R.id.menu_item_search_all:
                    return true;
                case R.id.menu_item_search_checked:
                    return true;
                case R.id.menu_item_search_unchecked:
                    return true;
                case R.id.menu_item_search_name:
                    return true;
                case R.id.menu_item_search_keyword:
                    return true;
                case R.id.menu_item_search_price:
                    return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
