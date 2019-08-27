package com.example.simplebuylist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository repository;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
    }

    public Boolean insert(Item item) throws ExecutionException, InterruptedException {
        return repository.insert(item);
    }

    public Boolean update(Item item) throws ExecutionException, InterruptedException {
        return repository.update(item);
    }

    public Boolean delete(Item item) throws ExecutionException, InterruptedException {
        return repository.delete(item);
    }

    public LiveData<Item> getItem(Item item) throws ExecutionException, InterruptedException {
        return repository.getItem(item);
    }




    /* SORT BY NAMES */
    public List<Item> sortAToZ(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortAToZ(storeName);
    }

    public List<Item> sortZToA(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortZToA(storeName);
    }

    public List<Item> sortPurchasedAToZ(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortPurchasedAToZ(storeName);
    }

    public List<Item> sortPurchasedZToA(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortPurchasedZToA(storeName);
    }

    public List<Item> sortUnpurchasedAToZ(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortUnpurchasedAToZ(storeName);
    }

    public List<Item> sortUnpurchasedZToA(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortUnpurchasedZToA(storeName);
    }




    /* SORT BY PRICE */
    public List<Item> sortCheapest(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheapest(storeName);
    }

    public List<Item> sortMostExpensive(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortMostExpensive(storeName);
    }

    public List<Item> sortCheapestPurchase(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheapestPurchase(storeName);
    }

    public List<Item> sortMostExpensivePurchase(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortMostExpensivePurchase(storeName);
    }

    public List<Item> sortCheapestUnpurchased(String storeName) throws ExecutionException, InterruptedException {
        return repository.sortCheapestUnpurchased(storeName);
    }




    /* DELETE ITEMS */
    public void deleteEverything() {
        repository.deleteEverything();
    }

    public void deleteAllItems(String storeName) {
        repository.deleteAllItems(storeName);
    }

    public void deleteAllPurchased(String storeName) {
        repository.deleteAllPurchased(storeName);
    }

    public void deleteAllUnpurchased(String storeName) {
        repository.deleteAllUnpurchased(storeName);
    }




    /* FILTER ITEMS BY NAME, KEYWORD, OR TYPE */
    public List<Item> getItemsByName(String storeName, String name) throws ExecutionException, InterruptedException {
        return repository.getItemsByName(storeName, name);
    }

    public List<Item> getItemsByKeyword(String storeName, String keyword) throws ExecutionException, InterruptedException {
        return repository.getItemsByKeyword(storeName, keyword);
    }

    public List<Item> getPurchasedItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.getPurchasedItems(storeName);
    }

    public List<Item> getUnpurchasedItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.getUnpurchasedItems(storeName);
    }

    public List<Item> getAllItems(String storeName) throws ExecutionException, InterruptedException {
        return repository.getAllItems(storeName);
    }




    /* FILTER ITEMS BY PRICE */
    public List<Item> getItemsWithPriceEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return repository.getItemsWithPriceEqualTo(storeName, price);
    }

    public List<Item> getItemsWithPriceNotEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return repository.getItemsWithPriceNotEqualTo(storeName, price);
    }

    public List<Item> getItemsWithPriceLessThan(String storeName, double price) throws ExecutionException, InterruptedException {
        return repository.getItemsWithPriceLessThan(storeName, price);
    }

    public List<Item> getItemsWithPriceLessThanOrEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return repository.getItemsWithPriceLessThanOrEqualTo(storeName, price);
    }

    public List<Item> getItemsWithPriceGreaterThan(String storeName, double price) throws ExecutionException, InterruptedException {
        return repository.getItemsWithPriceGreaterThan(storeName, price);
    }

    public List<Item> getItemsWithPriceGreaterThanOrEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return repository.getItemsWithPriceGreaterThanOrEqualTo(storeName, price);
    }
}
