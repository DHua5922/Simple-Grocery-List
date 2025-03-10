package com.dylanhua.simplebuylist;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * This class represents the table in SQLite Room database that has
 * all of the items that the user will buy.
 *
 * Columns:
 *      - id (row id)
 *      - name (item name)
 *      - price (item price)
 *      - wasPurchased (item was bought or not)
 *      - order (item's index in this table)
 *      - storeName (store name that the item will be bought at)
 */
@Entity(tableName="ITEM_TABLE",
        indices = {@Index(value = {"storeName"})},
        foreignKeys = @ForeignKey(entity = Store.class,
                parentColumns = "storeName",
                childColumns = "storeName",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE))
public class Item {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private double price;
    private int wasPurchased;
    private int order;
    private String storeName;

    /**
     * Constructs a new item with the given name, price, indication of whether this
     * item was purchased or not, order, and store name.
     *
     * @param id            id
     * @param name          name
     * @param price         price
     * @param wasPurchased  indication of whether this item was purchased or not
     * @param order         order
     * @param storeName     store name
     */
    public Item(long id, String name, double price, int wasPurchased, int order, String storeName) {
        setId(id);
        setName(name);
        setPrice(price);
        setWasPurchased(wasPurchased);
        setOrder(order);
        setStoreName(storeName);
    }

    /**
     * Constructs a new item with the given item.
     *
     * @param item  given item
     */
    public Item(Item item) {
        setId(item.getId());
        setName(item.getName());
        setPrice(item.getPrice());
        setWasPurchased(item.wasPurchased());
        setOrder(item.getOrder());
        setStoreName(item.getStoreName());
    }

    /**
     * Gets the id of this item.
     *
     * @return  id of this item
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of this item to the given id.
     *
     * @param id given id
     */
    public void setId(long id) {
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
    public int wasPurchased() {
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

    public boolean equals(Item item) {
        return (
            this.getId() == item.getId() &&
            this.getName().equals(item.getName()) &&
            this.getPrice() == item.getPrice() &&
            this.getOrder() == item.getOrder() &&
            this.wasPurchased() == item.wasPurchased() &&
            this.getStoreName().equals(item.getStoreName())
        );
    }
}
