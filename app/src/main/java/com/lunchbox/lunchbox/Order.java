package com.lunchbox.lunchbox;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;



public class Order implements Serializable{

    private String orderId;
    private String uid;
    private Timestamp timestamp;
    private double totalAmount;
    private String transactionId;
    private List<Meal> meals;

    public Order() {
    }

    public Order(String orderId, String uid, Timestamp timestamp, double totalAmount, String transactionId, List<Meal> meals) {
        this.orderId = orderId;
        this.uid = uid;
        this.timestamp = timestamp;
        this.totalAmount = totalAmount;
        this.transactionId = transactionId;
        this.meals = meals;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", uid='" + uid + '\'' +
                ", timestamp=" + timestamp +
                ", totalAmount=" + totalAmount +
                ", transactionId='" + transactionId + '\'' +
                ", meals=" + meals +
                '}';
    }
}
