package com.example.simplebuylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, AdapterView.OnItemSelectedListener {

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

        // remove default toolbar title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // make add button add new item
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

        // create initial list of store labels
        ArrayAdapter<String> adapter = null;
        final Spinner spinner = findViewById(R.id.list_label);
        try {
            spinner.setOnItemSelectedListener(this);
            // Create an ArrayAdapter using the string array and a custom spinner layout
            adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown, R.id.spinner_item_label, viewModel.getAllStoreNames());
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_dropdown);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<String> finalAdapter = adapter;
        viewModel.getAllStores().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> stores) {
                try {
                    // create default store if there are no stores
                    if(stores.size() == 0) {
                        Store fillerStore = new Store(1, "Unnamed");
                        long id = viewModel.insert(fillerStore);
                        if(id > 0) {
                            fillerStore.setId(id);
                            currentStore = new Store(fillerStore);
                        }
                    }
                    // display current store if there are still stores left; otherwise, display last store
                    else {
                        currentStore = (currentStore != null) ? currentStore : stores.get(stores.size() - 1);
                    }
                    // set list label
                    STORE_NAME = currentStore.getStoreName();
                    finalAdapter.clear();
                    finalAdapter.addAll(viewModel.getAllStoreNames());
                    spinner.setSelection(finalAdapter.getPosition(STORE_NAME));
                    // set item list
                    itemAdapter.setItemList(viewModel.getItemList(STORE_NAME));
                    // display total price of current list
                    displayTotalPrice(itemAdapter.getItemList());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        viewModel.observeAllItems(STORE_NAME).observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                try {
                    displayTotalPrice(viewModel.getItemList(STORE_NAME));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // drag and drop animation
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            /**
             * Update position of items as item display is being dragged to a new position.
             *
             * @param   recyclerView    list display
             * @param   dragged         item display being dragged
             * @param   target          item display after drag and drop
             */
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder dragged, RecyclerView.ViewHolder target) {
                int fromPosition = dragged.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                List<Item> list = itemAdapter.getItemList();

                // drag down
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        // swap item order
                        Collections.swap(list, i, i + 1);

                        int order1 = list.get(i).getOrder();
                        int order2 = list.get(i + 1).getOrder();
                        list.get(i).setOrder(order2);
                        list.get(i + 1).setOrder(order1);
                    }
                }
                // drag up
                else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        // swap item order
                        Collections.swap(list, i, i - 1);

                        int order1 = list.get(i).getOrder();
                        int order2 = list.get(i - 1).getOrder();
                        list.get(i).setOrder(order2);
                        list.get(i - 1).setOrder(order1);
                    }
                }
                // notify adapter that item was dragged to new position
                itemAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * After item was dragged to a new position, update items with new position in the database.
             *
             * @param   recyclerView    list display
             * @param   viewHolder      item display
             */
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                try {
                    viewModel.update(itemAdapter.getItemList());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(itemListView);
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
                    DialogAction.createList(this, "Enter a name for the new list", viewModel);
                    break;
                case R.id.option_rename_list:
                    DialogAction.renameList(this, "Enter a new name for this list", viewModel);
                    break;
                case R.id.option_delete_list:
                    DialogAction.confirmListDeletion(this, "Are you sure you want to delete this list?", viewModel);
                    break;
                case R.id.option_delete_all_lists:
                    DialogAction.confirmAllListDeletion(this, "Are you sure you want to delete all your lists?", viewModel);
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
                Item item;

                if (requestCode == ADD_ITEM_REQUEST) {
                    item = new Item(
                            data.getLongExtra(ItemAdapter.EXTRA_ITEM_ID, 0),
                            name,
                            data.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0),
                            data.getIntExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, 0),
                            itemAdapter.getHighestOrder() + 1,
                            STORE_NAME
                    );
                    if (itemAdapter.add(item))
                        Text.printMessage(this, name + " has been added", Text.SUCCESS);
                    else
                        Text.printMessage(this, name + " could not be added", Text.FAIL);
                } else if (requestCode == ItemAdapter.EDIT_ITEM_REQUEST) {
                    item = itemAdapter.getItem(itemAdapter.getClickedItemDisplay());
                    item.setName(name);
                    item.setPrice(data.getDoubleExtra(ItemAdapter.EXTRA_ITEM_PRICE, 0));
                    item.setWasPurchased(data.getIntExtra(ItemAdapter.EXTRA_ITEM_IS_BOUGHT, 0));

                    if (itemAdapter.update(item))
                        Text.printMessage(this, "Changes to " + name + " has been saved", Text.SUCCESS);
                    else
                        Text.printMessage(this, "Changes to " + name + " could not be saved", Text.FAIL);
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
                    itemAdapter.setItemList(viewModel.sortPriceIncreasing(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_price_decreasing:
                    itemAdapter.setItemList(viewModel.sortPriceDecreasing(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_checked_price_increasing:
                    itemAdapter.setItemList(viewModel.sortCheckedPriceIncreasing(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_checked_price_decreasing:
                    itemAdapter.setItemList(viewModel.sortCheckedPriceDecreasing(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_unchecked_price_increasing:
                    itemAdapter.setItemList(viewModel.sortUncheckedPriceIncreasing(STORE_NAME));
                    return true;
                case R.id.menu_item_sort_unchecked_price_decreasing:
                    itemAdapter.setItemList(viewModel.sortUncheckedPriceDecreasing(STORE_NAME));
                    return true;



                case R.id.menu_item_delete_all:
                    DialogAction.confirmAllItemDeletion(this, "Are you sure to want to delete all your items?", itemAdapter, viewModel);
                    return true;
                case R.id.menu_item_delete_checked:
                    DialogAction.confirmAllCheckedItemDeletion(this, "Are you sure to want to delete all your purchased items?", itemAdapter, viewModel);
                    return true;
                case R.id.menu_item_delete_unchecked:
                    DialogAction.confirmAllUncheckedItemDeletion(this, "Are you sure to want to delete all your unpurchased items?", itemAdapter, viewModel);
                    return true;



                case R.id.menu_item_search_all:
                    itemAdapter.setItemList(viewModel.getItemList(STORE_NAME));
                    return true;
                case R.id.menu_item_search_checked:
                    itemAdapter.setItemList(viewModel.getCheckedItems(STORE_NAME));
                    return true;
                case R.id.menu_item_search_unchecked:
                    itemAdapter.setItemList(viewModel.getUncheckedItems(STORE_NAME));
                    return true;
                case R.id.menu_item_search_name:
                    DialogAction.searchName(this, "Enter the name of the item to search for.", itemAdapter, viewModel);
                    return true;
                case R.id.menu_item_search_keyword:
                    DialogAction.searchKeyword(this, "Enter the keyword to search items for.", itemAdapter, viewModel);
                    return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public native double getTotalPrice(Object[] itemArray);

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        ViewModel viewModel = new ViewModel(getApplication());
        STORE_NAME = parent.getItemAtPosition(pos).toString();
        try {
            currentStore = viewModel.getGivenStore(STORE_NAME);
            itemAdapter.setItemList(viewModel.getItemList(STORE_NAME));
            displayTotalPrice(itemAdapter.getItemList());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void displayTotalPrice(List<Item> itemList) {
        TextView totalPriceDisplay = findViewById(R.id.total_price);
        Item[] itemArray = makeArray(itemList);
        totalPriceDisplay.setText(getString(R.string.total_price, getTotalPrice(itemArray)));
    }

    private Item[] makeArray(List<Item> itemList) {
        Item[] itemArray = new Item[itemList.size()];

        for(int i = 0; i < itemList.size(); i++) {
            itemArray[i] = new Item(itemList.get(i));
        }

        return itemArray;
    }
}
