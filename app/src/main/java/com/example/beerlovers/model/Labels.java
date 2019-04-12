package com.example.beerlovers.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Labels implements Parcelable {
    private int id;
    private String icon;
    private String medium;
    private String large;
    private String contentAwareIcon;
    private String contentAwareMedium;
    private String contentAwareLarge;


    // Getter Methods

    protected Labels(Parcel in) {
        id = in.readInt();
        icon = in.readString();
        medium = in.readString();
        large = in.readString();
        contentAwareIcon = in.readString();
        contentAwareMedium = in.readString();
        contentAwareLarge = in.readString();
    }

    public static final Creator<Labels> CREATOR = new Creator<Labels>() {
        @Override
        public Labels createFromParcel(Parcel in) {
            return new Labels(in);
        }

        @Override
        public Labels[] newArray(int size) {
            return new Labels[size];
        }
    };

    public String getIcon() {
        return icon;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }

    public String getContentAwareIcon() {
        return contentAwareIcon;
    }

    public String getContentAwareMedium() {
        return contentAwareMedium;
    }

    public String getContentAwareLarge() {
        return contentAwareLarge;
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

    public void setContentAwareIcon(String contentAwareIcon) {
        this.contentAwareIcon = contentAwareIcon;
    }

    public void setContentAwareMedium(String contentAwareMedium) {
        this.contentAwareMedium = contentAwareMedium;
    }

    public void setContentAwareLarge(String contentAwareLarge) {
        this.contentAwareLarge = contentAwareLarge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(icon);
        dest.writeString(medium);
        dest.writeString(large);
        dest.writeString(contentAwareIcon);
        dest.writeString(contentAwareMedium);
        dest.writeString(contentAwareLarge);
    }
}