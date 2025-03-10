package com.dylanhua.simplebuylist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public long insert(Store store) throws ExecutionException, InterruptedException {
        return repository.insert(store);
    }

    public Boolean update(Store store) throws ExecutionException, InterruptedException {
        return repository.update(store) > 0;
    }

    public Boolean delete(Store store) throws ExecutionException, InterruptedException {
        return repository.delete(store) > 0;
    }

    public Boolean deleteAll() throws ExecutionException, InterruptedException {
        return repository.deleteAll() > 0;
    }

    public Store getGivenStore(String storeName) throws ExecutionException, InterruptedException {
        return repository.getGivenStore(storeName);
    }

    public List<String> getAllStoreNames() throws ExecutionException, InterruptedException {
        return repository.getAllStoreNames();
    }

    public LiveData<List<Store>> getAllStores() {
        return repository.getAllStores();
    }


    public long insert(Item item) throws ExecutionException, InterruptedException {
        return repository.insert(item);
    }

    public Boolean update(Item item) throws ExecutionException, InterruptedException {
        return repository.update(item) > 0;
    }

    public Boolean update(List<Item> itemList) throws ExecutionException, InterruptedException {
        return repository.update(itemList) > 0;
    }

    public Boolean delete(Item item) throws ExecutionException, InterruptedException {
        return repository.delete(item) > 0;
    }

    public List<String> getAllItemNames(String storeName) throws ExecutionException, InterruptedException {
        return repository.getAllItemNames(storeName);
    }

    public LiveData<List<Item>> observeAllItems(String storeName) {
        return repository.observeAllItems(storeName);
    }




    public List<Item> sortItemsAToZ(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortItemsAToZ(storeName);
    }

    public List<Item> sortItemsZToA(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortItemsZToA(storeName);
    }

    public List<Item> sortCheckedItemsAToZ(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheckedItemsAToZ(storeName);
    }

    public List<Item> sortCheckedItemsZToA(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheckedItemsZToA(storeName);
    }

    public List<Item> sortUncheckedItemsAToZ(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortUncheckedItemsAToZ(storeName);
    }

    public List<Item> sortUncheckedItemsZToA(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortUncheckedItemsZToA(storeName);
    }





    public List<Item> sortPriceIncreasing(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortPriceIncreasing(storeName);
    }

    public List<Item> sortPriceDecreasing(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortPriceDecreasing(storeName);
    }

    public List<Item> sortCheckedPriceIncreasing(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheckedPriceIncreasing(storeName);
    }

    public List<Item> sortCheckedPriceDecreasing(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheckedPriceDecreasing(storeName);
    }

    public List<Item> sortUncheckedPriceIncreasing(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortUncheckedPriceIncreasing(storeName);
    }

    public List<Item> sortUncheckedPriceDecreasing(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortUncheckedPriceDecreasing(storeName);
    }




    public boolean deleteAllItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.deleteAllItems(storeName) > 0;
    }

    public boolean deleteAllCheckedItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.deleteAllCheckedItems(storeName) > 0;
    }

    public boolean deleteAllUncheckedItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.deleteAllUncheckedItems(storeName) > 0;
    }




    public List<Item> getItemList(String storeName) throws ExecutionException, InterruptedException {
        return repository.getItemList(storeName);
    }

    public List<Item> getCheckedItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.getCheckedItems(storeName);
    }

    public List<Item> getUncheckedItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.getUncheckedItems(storeName);
    }

    public List<Item> getItemsByName(String storeName, String name) throws ExecutionException, InterruptedException {
        return repository.getItemsByName(storeName, name);
    }

    public List<Item> getItemsByKeyword(String storeName, String keyword) throws ExecutionException, InterruptedException {
        return repository.getItemsByKeyword(storeName, keyword);
    }
}
