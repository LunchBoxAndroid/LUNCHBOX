package com.lunchbox.lunchbox;

import java.io.Serializable;

public class AddOnItem implements Serializable{
    public int itemId;
    public String itemName;
    public double itemPrice;
    public boolean isVeg;
    public int quantity;

    public AddOnItem(int itemId, String itemName, double itemPrice, boolean isVeg, int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.isVeg = isVeg;
        this.quantity = quantity;
    }

    public AddOnItem(String itemName, double itemPrice, boolean isVeg, int quantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.isVeg = isVeg;
        this.quantity = quantity;
    }

    public AddOnItem(AddOnItem addOnItem){
        this.itemName = addOnItem.itemName;
        this.itemPrice = addOnItem.itemPrice;
        this.isVeg = addOnItem.isVeg;
        this.quantity = addOnItem.quantity;
    }
}
