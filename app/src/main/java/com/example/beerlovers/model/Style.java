package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Style")
public class Style {
    @PrimaryKey
    @NonNull
    private float id;
    private float categoryId;
    Category CategoryObject;
    private String name;
    private String shortName;
    private String description;
    private String ibuMin;
    private String ibuMax;
    private String abvMin;
    private String abvMax;
    private String srmMin;
    private String srmMax;
    private String ogMin;
    private String fgMin;
    private String fgMax;
    private String createDate;
    private String updateDate;


    // Getter Methods

    public float getId() {
        return id;
    }

    public float getCategoryId() {
        return categoryId;
    }

    public Category getCategory() {
        return CategoryObject;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getIbuMin() {
        return ibuMin;
    }

    public String getIbuMax() {
        return ibuMax;
    }

    public String getAbvMin() {
        return abvMin;
    }

    public String getAbvMax() {
        return abvMax;
    }

    public String getSrmMin() {
        return srmMin;
    }

    public String getSrmMax() {
        return srmMax;
    }

    public String getOgMin() {
        return ogMin;
    }

    public String getFgMin() {
        return fgMin;
    }

    public String getFgMax() {
        return fgMax;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setCategoryId(float categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory(Category categoryObject) {
        this.CategoryObject = categoryObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIbuMin(String ibuMin) {
        this.ibuMin = ibuMin;
    }

    public void setIbuMax(String ibuMax) {
        this.ibuMax = ibuMax;
    }

    public void setAbvMin(String abvMin) {
        this.abvMin = abvMin;
    }

    public void setAbvMax(String abvMax) {
        this.abvMax = abvMax;
    }

    public void setSrmMin(String srmMin) {
        this.srmMin = srmMin;
    }

    public void setSrmMax(String srmMax) {
        this.srmMax = srmMax;
    }

    public void setOgMin(String ogMin) {
        this.ogMin = ogMin;
    }

    public void setFgMin(String fgMin) {
        this.fgMin = fgMin;
    }

    public void setFgMax(String fgMax) {
        this.fgMax = fgMax;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
