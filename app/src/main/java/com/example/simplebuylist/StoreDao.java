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

    @Query("DELETE FROM STORE_TABLE")
    int deleteAll();

    @Query("SELECT * FROM STORE_TABLE WHERE storeName = :storeName")
    Store getGivenStore(String storeName);

    @Query("SELECT storeName FROM STORE_TABLE")
    List<String> getAllNames();

    @Query("SELECT * FROM STORE_TABLE")
    LiveData<List<Store>> getAllStores();
}
