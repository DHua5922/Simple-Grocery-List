package com.example.simplebuylist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class represents the table in SQLite Room database that has
 * all of the stores that the user will buy at.
 *
 * Columns:
 *      - id (row id)
 *      - storeName (store name that the user will buy at)
 *
 * Important Note:
 *      - Updating
 *          Updating a store name will also update the store name of
 *          all the items with the previous store name in the table
 *          with all the items to be purchased
 *      - Deleting
 *          Deleting a store name will also delete all the items with
 *          the same store name in the table with all the items to
 *          be purchased at
 */
@Entity(tableName="STORE_TABLE",
        primaryKeys = {"id", "storeName"})
public class Store {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String storeName;

    /**
     * Constructs the new store to buy at with the given id and store name.
     *
     * @param id        id
     * @param storeName store name
     */
    public Store(int id, String storeName) {
        setId(id);
        setName(storeName);
    }

    /**
     * Gets the id of this store.
     *
     * @return  id of this store
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this store to the given id.
     *
     * @param id given id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of this store.
     *
     * @return  name of this store
     */
    public String getName() {
        return storeName;
    }

    /**
     * Sets the name of this store that that the user will buy at to the given store name.
     *
     * @param storeName given store name
     */
    public void setName(String storeName) {
        this.storeName = storeName;
    }
}
