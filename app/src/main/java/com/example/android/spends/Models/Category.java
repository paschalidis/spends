package com.example.android.spends.Models;

public class Category {

    private Integer id;
    private String title;
    private String description;

    public Category(){}

    public Category(Integer _id, String _title) {
        this.id = _id;
        this.title = _title;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer _id) {
        this.id = _id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String _title) {
        this.title = _title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String _description) {
        this.description = _description;
    }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return title;
    }
}
