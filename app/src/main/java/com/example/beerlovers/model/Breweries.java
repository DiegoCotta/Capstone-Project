package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "Breweries")
public class Breweries implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String nameShortDisplay;
    private String description;
    private String website;
    private String established;
    private String isOrganic;
    @SerializedName("labels")
    Labels Images;
    private String status;
    private String statusDisplay;
    private String createDate;
    private String updateDate;
    private String isMassOwned;
    private String isInBusiness;
    private String isVerified;
    List<Location> locations;


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
        status = in.readString();
        statusDisplay = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        isMassOwned = in.readString();
        isInBusiness = in.readString();
        isVerified = in.readString();
        locations = in.createTypedArrayList(Location.CREATOR);
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

    public String getEstablished() {
        return established;
    }

    public String getIsOrganic() {
        return isOrganic;
    }


    public String getStatus() {
        return status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getIsMassOwned() {
        return isMassOwned;
    }

    public String getIsInBusiness() {
        return isInBusiness;
    }

    public String getIsVerified() {
        return isVerified;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setIsMassOwned(String isMassOwned) {
        this.isMassOwned = isMassOwned;
    }

    public void setIsInBusiness(String isInBusiness) {
        this.isInBusiness = isInBusiness;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public Labels getImages() {
        return Images;
    }

    public void setImages(Labels images) {
        Images = images;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
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
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(isMassOwned);
        dest.writeString(isInBusiness);
        dest.writeString(isVerified);
        dest.writeTypedList(locations);
    }
}