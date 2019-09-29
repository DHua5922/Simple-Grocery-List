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
    Boolean insert(Store store);

    @Update
    Boolean update(Store store);

    @Delete
    Boolean delete(Store store);

    @Query("SELECT storeName FROM STORE_TABLE WHERE storeName = :storeName")
    LiveData<Store> getStore(String storeName);

    @Query("SELECT * FROM STORE_TABLE")
    List<Store> getAllStores();
}
