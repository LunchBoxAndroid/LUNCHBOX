package com.lunchbox.lunchbox;

import com.google.firebase.Timestamp;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order {
    private String orderId;
    private String uid;
    private Timestamp timestamp;
    private List<Date> dates;
//    private boolean dailyOrder;
//    private boolean bulkOrder;
//    private boolean addOn;
    private Map<String,Boolean> orderType;
    private double price;
    private int  bulkItemCount;     // In case of BulkOrders
    private List<String> items;     // in case of AddOnOrders
    private String transactionId;
    private boolean mealType;       // 0 - Lunch ; 1 - Dinner

    public Order(String orderId, String uid, Map<String, Boolean> orderType, boolean mealType) {
        this.orderId = orderId;
        this.uid = uid;
        this.orderType = orderType;
        this.mealType = mealType;
    }

    public Order() {
    }

    public Order(String orderId, String uid, Timestamp timestamp, List<Date> dates, Map<String, Boolean> orderType, double price) {     //Constructor for DailyOrders
        this.orderId = orderId;
        this.uid = uid;
        this.timestamp = timestamp;
        this.dates = dates;
        this.orderType = orderType;
        this.price = price;
    }

    public Order(String orderId, String uid, Timestamp timestamp, Map<String, Boolean> orderType, double price, int bulkItemCount) {    //Constructor for BulkOrders
        this.orderId = orderId;
        this.uid = uid;
        this.timestamp = timestamp;
        this.orderType = orderType;
        this.price = price;
        this.bulkItemCount = bulkItemCount;
    }

    public Order(String orderId, String uid, Timestamp timestamp, Map<String, Boolean> orderType, double price, List<String> items) {   //Constructor for AddOnOrders
        this.orderId = orderId;
        this.uid = uid;
        this.timestamp = timestamp;
        this.orderType = orderType;
        this.price = price;
        this.items = items;
    }

    public Order(String orderId, String uid, Timestamp timestamp, List<Date> dates, Map<String, Boolean> orderType, double price, int bulkItemCount, List<String> items, String transactionId) {
        this.orderId = orderId;
        this.uid = uid;
        this.timestamp = timestamp;
        this.dates = dates;
        this.orderType = orderType;
        this.price = price;
        this.bulkItemCount = bulkItemCount;
        this.items = items;
        this.transactionId = transactionId;
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

    public Map<String, Boolean> getOrderType() {
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

    public boolean isMealType() {
        return mealType;
    }

    public void setMealType(boolean mealType) {
        this.mealType = mealType;
    }
}
