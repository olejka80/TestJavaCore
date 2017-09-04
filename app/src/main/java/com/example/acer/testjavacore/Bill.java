package com.example.acer.testjavacore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Bill {

    private String mTypeOfCoffee;
    private double mPrice;
    private String mDateTime;
    private long mCheckNumber;

    public Bill() {

    }

    public Bill(long checkNumber, String typeOfCoffee, double price, String dateTime) {
        mCheckNumber = checkNumber;
        mTypeOfCoffee = typeOfCoffee;
        mPrice = price;
        mDateTime = dateTime;
    }

    public String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    public long getCheckNumber() {
        return mCheckNumber;
    }

    public void setCheckNumber(long checkNumber) {
        mCheckNumber = checkNumber;
    }

    public String getTypeOfCoffee() {
        return mTypeOfCoffee;
    }

    public void setTypeOfCoffee(String typeOfCoffee) {
        mTypeOfCoffee = typeOfCoffee;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public void setDateTime(String dateTime) {
        mDateTime = dateTime;
    }

    public boolean addToArray(String[] billsInfo, ArrayList<Bill> bills) {
        long checkNumber = bills.size();
        String typeOfCoffee = billsInfo[1];
        double price = Double.valueOf(billsInfo[2]);
        String dateTime = billsInfo[3];
        bills.add(new Bill(checkNumber, typeOfCoffee, price, dateTime));
        return true;
    }
}
