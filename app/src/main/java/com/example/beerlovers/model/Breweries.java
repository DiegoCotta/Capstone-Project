package com.example.beerlovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Breweries implements Parcelable {
    private String id;
    private String name;
    private String nameShortDisplay;
    private String description;
    private String website;
    private String established;
    private String isOrganic;
    @SerializedName("labels")
    Labels Images;


    // Getter Methods

    protected Breweries(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameShortDisplay = in.readString();
        description = in.readString();
        website = in.readString();
        established = in.readString();
        isOrganic = in.readString();
        Images = in.readParcelable(Labels.class.getClassLoader());

    }

    public static final Creator<Breweries> CREATOR = new Creator<Breweries>() {
        @Override
        public Breweries createFromParcel(Parcel in) {
            return new Breweries(in);
        }

        @Override
        public Breweries[] newArray(int size) {
            return new Breweries[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameShortDisplay() {
        return nameShortDisplay;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }


    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameShortDisplay(String nameShortDisplay) {
        this.nameShortDisplay = nameShortDisplay;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }


    public Labels getImages() {
        return Images;
    }

    public void setImages(Labels images) {
        Images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nameShortDisplay);
        dest.writeString(description);
        dest.writeString(website);
        dest.writeString(established);
        dest.writeString(isOrganic);
        dest.writeParcelable(Images, flags);
    }
}