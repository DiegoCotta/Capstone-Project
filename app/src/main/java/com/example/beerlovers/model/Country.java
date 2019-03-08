package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Country")
public class Country {
    @PrimaryKey
    @NonNull
    private String isoCode;
    private String name;
    private String displayName;
    private String isoThree;
    private float numberCode;
    private String createDate;


    // Getter Methods

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIsoThree() {
        return isoThree;
    }

    public float getNumberCode() {
        return numberCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    // Setter Methods

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setIsoThree(String isoThree) {
        this.isoThree = isoThree;
    }

    public void setNumberCode(float numberCode) {
        this.numberCode = numberCode;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
