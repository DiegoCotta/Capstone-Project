package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "Location")
public class Location implements Parcelable {
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

    protected Location(Parcel in) {
        id = in.readString();
        name = in.readString();
        streetAddress = in.readString();
        locality = in.readString();
        region = in.readString();
        postalCode = in.readString();
        phone = in.readString();
        website = in.readString();
        hoursOfOperation = in.readString();
        latitude = in.readFloat();
        longitude = in.readFloat();
        isPrimary = in.readString();
        inPlanning = in.readString();
        isClosed = in.readString();
        openToPublic = in.readString();
        locationType = in.readString();
        locationTypeDisplay = in.readString();
        countryIsoCode = in.readString();
        yearOpened = in.readString();
        status = in.readString();
        statusDisplay = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        hoursOfOperationNotes = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(streetAddress);
        dest.writeString(locality);
        dest.writeString(region);
        dest.writeString(postalCode);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeString(hoursOfOperation);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeString(isPrimary);
        dest.writeString(inPlanning);
        dest.writeString(isClosed);
        dest.writeString(openToPublic);
        dest.writeString(locationType);
        dest.writeString(locationTypeDisplay);
        dest.writeString(countryIsoCode);
        dest.writeString(yearOpened);
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(hoursOfOperationNotes);
    }
}