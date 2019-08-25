package com.example.simplebuylist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This Item class represents the item table in SQLite Room database.
 * Each property of this item represents a column in the Item table.
 */
@Entity(tableName="ITEM_TABLE")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double price;
    private int wasPurchased;
    private int order;
    private String storeName;

    /**
     * Constructs new item with given id, name, price, order, and store name.
     *
     * @param id        id
     * @param name      name
     * @param price     price
     * @param order     order
     * @param storeName store name
     */
    public Item(int id, String name, double price, int order, String storeName) {
        setId(id);
        setName(name);
        setPrice(price);
        setWasPurchased(0);
        setOrder(order);
        setStoreName(storeName);
    }

    /**
     * Constructs new item with given id, name, price, indication of whether this
     * item was purchased or not, order, and store name.
     *
     * @param id            id
     * @param name          name
     * @param price         price
     * @param wasPurchased  indication of whether this item was purchased or not
     * @param order         order
     * @param storeName     store name
     */
    public Item(int id, String name, double price, int wasPurchased, int order, String storeName) {
        setId(id);
        setName(name);
        setPrice(price);
        setWasPurchased(wasPurchased);
        setOrder(order);
        setStoreName(storeName);
    }

    /**
     * Gets the id of this item.
     *
     * @return  id of this item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this item to the given id.
     *
     * @param id given id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of this item.
     *
     * @return  name this item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this item to the given name.
     *
     * @param name given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of this item.
     *
     * @return  price this item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of this item to the given price.
     *
     * @param price given price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the indication of whether this item was purchased or not.
     *
     * @return  indication of whether this item was purchased or not
     */
    public int getWasPurchased() {
        return wasPurchased;
    }

    /**
     * Sets the indication of whether this item was purchased or not to the given indication.
     *
     * @param wasPurchased given indication of whether this item was purchased or not
     */
    public void setWasPurchased(int wasPurchased) {
        this.wasPurchased = wasPurchased;
    }

    /**
     * Gets the order of this item in the list.
     *
     * @return  order of this item in the list
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order of this item to the given order.
     *
     * @param order given order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the name of the store that this item will be purchased at.
     *
     * @return  name of the store that this item will be purchased at
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Sets the name of the store that this item will be purchased at to the given store name.
     *
     * @param storeName given store name
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
