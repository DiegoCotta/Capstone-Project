package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "Beer")
public class Beer implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String nameDisplay;
    private String description;
    private String abv;
    private String ibu;
    private float glasswareId;
    private float srmId;
    private float availableId;
    private float styleId;
    private String isOrganic;
    private String isRetired;
    private String status;
    private String foodPairings;
    private String statusDisplay;
    private String servingTemperature;
    private String servingTemperatureDisplay;
    private String originalGravity;
    private String createDate;
    private String updateDate;
    @Ignore
    private Labels labels;
    @Ignore
    private Glass glass;
    @Ignore
    private Style style;
    @Ignore
    private List<Breweries> breweries;

    @Ignore
    private Ingredients ingredients;

    public Beer(@NonNull String id, String name, String nameDisplay, String description, String abv, String ibu, float glasswareId, float srmId, float availableId, float styleId, String isOrganic, String isRetired, String status, String statusDisplay, String servingTemperature, String servingTemperatureDisplay, String originalGravity, String createDate, String updateDate) {
        this.id = id;
        this.name = name;
        this.nameDisplay = nameDisplay;
        this.description = description;
        this.abv = abv;
        this.ibu = ibu;
        this.glasswareId = glasswareId;
        this.srmId = srmId;
        this.availableId = availableId;
        this.styleId = styleId;
        this.isOrganic = isOrganic;
        this.isRetired = isRetired;
        this.status = status;
        this.statusDisplay = statusDisplay;
        this.servingTemperature = servingTemperature;
        this.servingTemperatureDisplay = servingTemperatureDisplay;
        this.originalGravity = originalGravity;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    @Ignore
    protected Beer(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameDisplay = in.readString();
        description = in.readString();
        abv = in.readString();
        ibu = in.readString();
        glasswareId = in.readFloat();
        srmId = in.readFloat();
        availableId = in.readFloat();
        styleId = in.readFloat();
        isOrganic = in.readString();
        isRetired = in.readString();
        status = in.readString();
        statusDisplay = in.readString();
        servingTemperature = in.readString();
        servingTemperatureDisplay = in.readString();
        originalGravity = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        foodPairings = in.readString();
        labels = in.readParcelable(Labels.class.getClassLoader());
        glass = in.readParcelable(Glass.class.getClassLoader());
        style = in.readParcelable(Style.class.getClassLoader());
        breweries = in.createTypedArrayList(Breweries.CREATOR);

        //ingredients = in.readParcelable(Ingredients.class.getClassLoader());
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public String getAbv() {
        return abv;
    }

    public String getIbu() {
        return ibu;
    }

    public float getGlasswareId() {
        return glasswareId;
    }

    public float getSrmId() {
        return srmId;
    }

    public float getAvailableId() {
        return availableId;
    }

    public float getStyleId() {
        return styleId;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public String getIsRetired() {
        return isRetired;
    }

    public Labels getLabels() {
        return labels;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public String getServingTemperature() {
        return servingTemperature;
    }

    public String getServingTemperatureDisplay() {
        return servingTemperatureDisplay;
    }

    public String getOriginalGravity() {
        return originalGravity;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public Glass getGlass() {
        return glass;
    }

    public Style getStyle() {
        return style;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public void setGlasswareId(float glasswareId) {
        this.glasswareId = glasswareId;
    }

    public void setSrmId(float srmId) {
        this.srmId = srmId;
    }

    public void setAvailableId(float availableId) {
        this.availableId = availableId;
    }

    public void setStyleId(float styleId) {
        this.styleId = styleId;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public void setIsRetired(String isRetired) {
        this.isRetired = isRetired;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public void setServingTemperature(String servingTemperature) {
        this.servingTemperature = servingTemperature;
    }

    public void setServingTemperatureDisplay(String servingTemperatureDisplay) {
        this.servingTemperatureDisplay = servingTemperatureDisplay;
    }

    public void setOriginalGravity(String originalGravity) {
        this.originalGravity = originalGravity;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }


    public List<Breweries> getBreweries() {
        return breweries;
    }

    public void setBreweries(List<Breweries> breweries) {
        this.breweries = breweries;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public String getFoodPairings() {
        return foodPairings;
    }

    public void setFoodPairings(String foodPairings) {
        this.foodPairings = foodPairings;
    }

    public static DiffUtil.ItemCallback<Beer> DIFF_CALLBACK = new DiffUtil.ItemCallback<Beer>() {
        @Override
        public boolean areItemsTheSame(@NonNull Beer oldItem, @NonNull Beer newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Beer oldItem, @NonNull Beer newItem) {
            return oldItem.equals(newItem);
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Beer beer = (Beer) obj;
        return beer.id.equals(this.id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nameDisplay);
        dest.writeString(description);
        dest.writeString(abv);
        dest.writeString(ibu);
        dest.writeFloat(glasswareId);
        dest.writeFloat(srmId);
        dest.writeFloat(availableId);
        dest.writeFloat(styleId);
        dest.writeString(isOrganic);
        dest.writeString(isRetired);
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(servingTemperature);
        dest.writeString(servingTemperatureDisplay);
        dest.writeString(originalGravity);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(foodPairings);
        dest.writeParcelable(labels, flags);
        dest.writeParcelable(glass, flags);
        dest.writeParcelable(style, flags);
        //   dest.writeParcelable(ingredients, flags);
        dest.writeTypedList(breweries);

    }
}