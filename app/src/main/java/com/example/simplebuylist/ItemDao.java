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

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName")
    List<Item> getItemList(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName")
    LiveData<List<Item>> getAllItems(String storeName);





    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY name ASC")
    List<Item> sortItemsAToZ(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY name DESC")
    List<Item> sortItemsZToA(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY name ASC")
    List<Item> sortCheckedItemsAToZ(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY name DESC")
    List<Item> sortCheckedItemsZToA(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY name ASC")
    List<Item> sortUncheckedItemsAToZ(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY name DESC")
    List<Item> sortUncheckedItemsZToA(String storeName);






    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY price ASC")
    List<Item> sortPriceIncreasing(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName ORDER BY price DESC")
    List<Item> sortPriceDecreasing(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY price ASC")
    List<Item> sortCheckedPriceIncreasing(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1 ORDER BY price DESC")
    List<Item> sortCheckedPriceDecreasing(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY price ASC")
    List<Item> sortUncheckedPriceIncreasing(String storeName);

    @Query("SELECT * FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0 ORDER BY price DESC")
    List<Item> sortUncheckedPriceDecreasing(String storeName);




    @Query("DELETE FROM ITEM_TABLE WHERE storeName = :storeName")
    int deleteAllItems(String storeName);

    @Query("DELETE FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 1")
    int deleteAllCheckedItems(String storeName);

    @Query("DELETE FROM ITEM_TABLE WHERE storeName = :storeName AND wasPurchased = 0")
    int deleteAllUncheckedItems(String storeName);
}