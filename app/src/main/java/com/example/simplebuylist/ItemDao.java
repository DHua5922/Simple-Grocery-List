package com.example.simplebuylist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    long insert(Item item);

    @Update
    int update(Item item);

    @Delete
    int delete(Item item);

    @Query("SELECT * FROM ITEM_TABLE WHERE id = :id")
    LiveData<Item> getItem(long id);

    /* SORT ITEMS BY NAMES */
    // sort items A - Z
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY name ASC")
    List<Item> sortAToZ(String storeName);

    // sort items Z - A
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY name DESC")
    List<Item> sortZToA(String storeName);

    // sort purchased items A - Z
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY name ASC")
    List<Item> sortPurchasedAToZ(String storeName);

    // sort purchased items Z - A
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY name DESC")
    List<Item> sortPurchasedZToA(String storeName);

    // sort unpurchased items A - Z
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY name ASC")
    List<Item> sortUnpurchasedAToZ(String storeName);

    // sort unpurchased items Z - A
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY name DESC")
    List<Item> sortUnpurchasedZToA(String storeName);

    /* SORT ITEMS BY PRICE */
    // sort items by lowest price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY price ASC")
    List<Item> sortCheapest(String storeName);

    // sort items by highest price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY price DESC")
    List<Item> sortMostExpensive(String storeName);

    // sort purchased items by lowest price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY price ASC")
    List<Item> sortCheapestPurchase(String storeName);

    // sort purchased items by highest price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY price DESC")
    List<Item> sortMostExpensivePurchase(String storeName);

    // sort unpurchased items by lowest price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY price ASC")
    List<Item> sortCheapestUnpurchased(String storeName);

    // sort unpurchased items by highest price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY price DESC")
    List<Item> sortMostExpensiveUnpurchased(String storeName);

    /* DELETE ITEMS */
    // delete all items in item table
    @Query("DELETE FROM ITEM_TABLE")
    void deleteEverything();

    // delete all items in given store
    @Query("DELETE FROM ITEM_TABLE WHERE storeName = :storeName")
    void deleteAllItems(String storeName);

    // delete all purchased items in given store
    @Query("DELETE FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1")
    void deleteAllPurchased(String storeName);

    // delete all unpurchased items in given store
    @Query("DELETE FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0")
    void deleteAllUnpurchased(String storeName);

    /* FILTER ITEMS */
    // get items in given store with given name
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND name = :name")
    List<Item> getItemsByName(String storeName, String name);

    // get items in given store with given price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND price = :price")
    List<Item> getItemsWithPriceEqualTo(String storeName, double price);

    // get items in given store that does not have given price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND price != :price")
    List<Item> getItemsWithPriceNotEqualTo(String storeName, double price);

    // get items in given store with price < given price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND price < :price")
    List<Item> getItemsWithPriceLessThan(String storeName, double price);

    // get items in given store with price <= given price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND price <= :price")
    List<Item> getItemsWithPriceLessThanOrEqualTo(String storeName, double price);

    // get items in given store with price > given price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND price > :price")
    List<Item> getItemsWithPriceGreaterThan(String storeName, double price);

    // get items in given store with price >= given price
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND price >= :price")
    List<Item> getItemsWithPriceGreaterThanOrEqualTo(String storeName, double price);

    // get items in given store with given keyword
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND name LIKE :keyword")
    List<Item> getItemsByKeyword(String storeName, String keyword);

    // get purchased items in given store
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1")
    List<Item> getPurchasedItems(String storeName);

    // get unpurchased items in given store
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0")
    List<Item> getUnpurchasedByName(String storeName);

    // get all items in given store
    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName")
    LiveData<List<Item>> getAllItems(String storeName);
}