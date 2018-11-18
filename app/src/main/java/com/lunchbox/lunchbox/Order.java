package com.lunchbox.lunchbox;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;



public class Order implements Serializable{
    public static int LUNCH = 0;
    public static int DINNER = 1;
    public static int BOTH = 2;

    public static int ORDER_DAILY = 0;
    public static int ORDER_BULK = 1;
    public static int ORDER_ADDON = 2;

    private String orderId;
    private String uid;
    private Timestamp timestamp;
    private List<Date> dates;
    private int orderType;
    private double price;
    private int  bulkItemCount;     // In case of BulkOrders
    private List<String> items;     // in case of AddOnOrders
    private String transactionId;
    private int mealType;       // 0 - Lunch ; 1 - Dinner ; 2 - Both

    public Order() {
    }

    public Order(String uid, int orderType, int mealType) {
        this.orderId = UUID.randomUUID().toString();
        this.uid = uid;
        this.orderType = orderType;
        this.mealType = mealType;
    }

    public Order(String uid, List<Date> dates, int orderType, double price, int mealType) {     //Constructor for DailyOrders
        this.orderId = UUID.randomUUID().toString();
        this.uid = uid;
        this.dates = dates;
        this.orderType = orderType;
        this.price = price;
        this.mealType = mealType;
    }

    public Order(String uid, List<Date> dates, int orderType, double price, int bulkItemCount, int mealType) {    //Constructor for BulkOrders
        this.orderId = UUID.randomUUID().toString();
        this.uid = uid;
        this.dates = dates;
        this.orderType = orderType;
        this.price = price;
        this.bulkItemCount = bulkItemCount;
        this.mealType = mealType;
    }

    public Order(String uid, int orderType, double price, List<String> items,int mealType) {   //Constructor for AddOnOrders
        this.orderId = UUID.randomUUID().toString();
        this.uid = uid;
        this.orderType = orderType;
        this.price = price;
        this.items = items;
        this.mealType = mealType;
    }

    public Order(String uid, Timestamp timestamp, List<Date> dates, int orderType, double price, int bulkItemCount, List<String> items, String transactionId, int mealType) {
        this.orderId = UUID.randomUUID().toString();
        this.uid = uid;
        this.timestamp = timestamp;
        this.dates = dates;
        this.orderType = orderType;
        this.price = price;
        this.bulkItemCount = bulkItemCount;
        this.items = items;
        this.transactionId = transactionId;
        this.mealType = mealType;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUid() {
        return uid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public List<Date> getDates() {
        return dates;
    }

    public int getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public int getBulkItemCount() {
        return bulkItemCount;
    }

    public List<String> getItems() {
        return items;
    }

    public String getTransactionId() {
        return transactionId;
    }



    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getMealType() {
        return mealType;
    }

    public void setMealType(int mealType) {
        this.mealType = mealType;
    }

    public String toString(){
        String res = "";
        res += "OrderId = " + orderId + ",\n uid = " + uid +
                ",\norderType = " + orderType +
                ",\nprice = " + price +
                ",\nbulkItemCount = " + bulkItemCount +
                ",\ntransactionId = " + transactionId +
                ",\nmealType = " + mealType +
                ",\ndates = ";
        if (dates != null)
            res += dates.toString();
        else
            res += "null";

        res += ",\nitems = " ;
        if (items != null)
            res += items.toString();
        else
            res += "null";

        res += ",\ntimestamp = ";
        if (items != null)
            res += timestamp.toString();
        else
            res += "null";
        return res;
    }
}
