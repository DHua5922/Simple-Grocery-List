package com.dylanhua.simplebuylist;

import androidx.room.Entity;
import androidx.room.Index;
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
        indices = {@Index(value = {"storeName"}, unique = true)})
public class Store {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String storeName;

    /**
     * Constructs the new store to buy at with the given id, store name, and total price.
     *
     * @param id            id
     * @param storeName     store name
     */
    public Store(long id, String storeName) {
        setId(id);
        setStoreName(storeName);
    }

    /**
     * Constructs a new store with the given store.
     * The information from the given store is copied to this store.
     *
     * @param store given store
     */
    public Store(Store store) {
        setId(store.getId());
        setStoreName(store.getStoreName());
    }

    /**
     * Gets the id of this store.
     *
     * @return  id of this store
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of this store to the given id.
     *
     * @param id given id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the name of this store.
     *
     * @return  name of this store
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Sets the name of this store that that the user will buy at to the given store name.
     *
     * @param storeName given store name
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
