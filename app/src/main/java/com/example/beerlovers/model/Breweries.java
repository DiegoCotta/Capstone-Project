package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;
@Entity(tableName = "Breweries")
public class Breweries {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String nameShortDisplay;
    private String description;
    private String website;
    private String established;
    private String isOrganic;
    Images ImagesObject;
    private String status;
    private String statusDisplay;
    private String createDate;
    private String updateDate;
    private String isMassOwned;
    private String isInBusiness;
    private String isVerified;
    List<Location> locations;


    // Getter Methods

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

    public Images getImages() {
        return ImagesObject;
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

    public void setImages(Images imagesObject) {
        this.ImagesObject = imagesObject;
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

    public Images getImagesObject() {
        return ImagesObject;
    }

    public void setImagesObject(Images imagesObject) {
        ImagesObject = imagesObject;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}