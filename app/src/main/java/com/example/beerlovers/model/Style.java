package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


public class Style implements Parcelable {
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

    public Style(){
    }
    // Getter Methods
    protected Style(Parcel in) {
        id = in.readFloat();
        categoryId = in.readFloat();
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        ibuMin = in.readString();
        ibuMax = in.readString();
        abvMin = in.readString();
        abvMax = in.readString();
        srmMin = in.readString();
        srmMax = in.readString();
        ogMin = in.readString();
        fgMin = in.readString();
        fgMax = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    public static final Creator<Style> CREATOR = new Creator<Style>() {
        @Override
        public Style createFromParcel(Parcel in) {
            return new Style(in);
        }

        @Override
        public Style[] newArray(int size) {
            return new Style[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(id);
        dest.writeFloat(categoryId);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(description);
        dest.writeString(ibuMin);
        dest.writeString(ibuMax);
        dest.writeString(abvMin);
        dest.writeString(abvMax);
        dest.writeString(srmMin);
        dest.writeString(srmMax);
        dest.writeString(ogMin);
        dest.writeString(fgMin);
        dest.writeString(fgMax);
        dest.writeString(createDate);
        dest.writeString(updateDate);
    }
}
