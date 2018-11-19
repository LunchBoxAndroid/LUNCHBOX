package com.lunchbox.lunchbox;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Meal implements Serializable {

    public static int LUNCH = 0;
    public static int DINNER = 1;

    private Date date;
    private int mealType;
    private List<Integer> addOn;
    private int mealCount;
    private String orderId;
    private String uid;

    public Meal() {
    }

    public Meal(Date date, int mealType, List<Integer> addOn, int mealCount) {
        this.date = date;
        this.mealType = mealType;
        this.addOn = addOn;
        this.mealCount = mealCount;
    }

    public Meal(Date date, int mealType, List<Integer> addOn, int mealCount, String orderId, String uid) {
        this.date = date;
        this.mealType = mealType;
        this.addOn = addOn;
        this.mealCount = mealCount;
        this.orderId = orderId;
        this.uid = uid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMealType(int mealType) {
        this.mealType = mealType;
    }

    public void setAddOn(List<Integer> addOn) {
        this.addOn = addOn;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }

    public Date getDate() {
        return date;
    }

    public int getMealType() {
        return mealType;
    }

    public List<Integer> getAddOn() {
        return addOn;
    }

    public int getMealCount() {
        return mealCount;
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

    @Override
    public String toString() {
        return "Meal{" +
                "date=" + date +
                ", mealType=" + mealType +
                ", addOn=" + addOn +
                ", mealCount=" + mealCount +
                '}';
    }
}




