package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Location")
public class Location {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String phone;
    private String website;
    private String hoursOfOperation;
    private float latitude;
    private float longitude;
    private String isPrimary;
    private String inPlanning;
    private String isClosed;
    private String openToPublic;
    private String locationType;
    private String locationTypeDisplay;
    private String countryIsoCode;
    private String yearOpened;
    private String status;
    private String statusDisplay;
    private String createDate;
    private String updateDate;
    private String hoursOfOperationNotes;
    Country CountryObject;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public String getRegion() {
        return region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public String getInPlanning() {
        return inPlanning;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public String getOpenToPublic() {
        return openToPublic;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getLocationTypeDisplay() {
        return locationTypeDisplay;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public String getYearOpened() {
        return yearOpened;
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

    public String getHoursOfOperationNotes() {
        return hoursOfOperationNotes;
    }

    public Country getCountry() {
        return CountryObject;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public void setInPlanning(String inPlanning) {
        this.inPlanning = inPlanning;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    public void setOpenToPublic(String openToPublic) {
        this.openToPublic = openToPublic;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setLocationTypeDisplay(String locationTypeDisplay) {
        this.locationTypeDisplay = locationTypeDisplay;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public void setYearOpened(String yearOpened) {
        this.yearOpened = yearOpened;
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

    public void setHoursOfOperationNotes(String hoursOfOperationNotes) {
        this.hoursOfOperationNotes = hoursOfOperationNotes;
    }

    public void setCountry(Country countryObject) {
        this.CountryObject = countryObject;
    }
}