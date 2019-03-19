package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "Country")
public class Country implements Parcelable {
    @PrimaryKey
    @NonNull
    private String isoCode;
    private String name;
    private String displayName;
    private String isoThree;
    private float numberCode;
    private String createDate;


    // Getter Methods

    protected Country(Parcel in) {
        isoCode = in.readString();
        name = in.readString();
        displayName = in.readString();
        isoThree = in.readString();
        numberCode = in.readFloat();
        createDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isoCode);
        dest.writeString(name);
        dest.writeString(displayName);
        dest.writeString(isoThree);
        dest.writeFloat(numberCode);
        dest.writeString(createDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

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
