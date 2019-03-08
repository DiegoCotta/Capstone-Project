package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Images")
public class Images {
    @PrimaryKey
    @NonNull
    private String icon;
    private String medium;
    private String large;
    private String squareMedium;
    private String squareLarge;


    // Getter Methods

    public String getIcon() {
        return icon;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }

    public String getSquareMedium() {
        return squareMedium;
    }

    public String getSquareLarge() {
        return squareLarge;
    }

    // Setter Methods

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public void setSquareMedium(String squareMedium) {
        this.squareMedium = squareMedium;
    }

    public void setSquareLarge(String squareLarge) {
        this.squareLarge = squareLarge;
    }
}
