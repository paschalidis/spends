package com.example.android.spends.Models;

import android.provider.BaseColumns;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Spend {
    private Integer id;
    private String amount;
    private String description;
    private Integer categoryID;
    private Integer locationID;
    private Integer date;

    public Spend(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getDateToString(){

        DateFormat sdf = new SimpleDateFormat("dd/MM  /yyyy HH:mm:ss");
        String  dateString = sdf.format(this.getDateFormat());
        return dateString;
    }

    public Date getDateFormat(){

        Integer dateTimestamp = this.getDate();
        Long dateLong = dateTimestamp.longValue();
        Date newDate = (new Date(dateLong * 1000L));

        return newDate;
    }
}
