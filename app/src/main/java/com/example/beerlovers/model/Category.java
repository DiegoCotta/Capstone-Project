package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Category")
public class Category {
    @PrimaryKey
    @NonNull
    private float id;
    private String name;
    private String createDate;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}