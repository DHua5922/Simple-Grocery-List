package com.example.simplebuylist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface StoreDao {
    @Insert
    long insert(Store store);

    @Update
    int update(Store store);

    @Delete
    int delete(Store store);

    @Query("SELECT * FROM STORE_TABLE LIMIT 1")
    Store getFirstStore();

    @Query("SELECT * FROM STORE_TABLE WHERE storeName = :storeName")
    LiveData<Store> getStore(String storeName);

    @Query("SELECT totalPrice FROM STORE_TABLE WHERE storeName = :storeName")
    LiveData<Double> getTotalPrice(String storeName);

    @Query("SELECT * FROM STORE_TABLE")
    List<Store> getAllStores();
}
