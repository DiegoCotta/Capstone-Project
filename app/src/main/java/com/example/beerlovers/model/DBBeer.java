package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Beer")
public class DBBeer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String abv;
    private String ibu;
    private boolean isOrganic;
    private boolean isRetired;
    private String foodPairings;
    private String icon;
    private String image;
    private String glass;
    private String style;
    private String breweries;
    private String ingredients;
    private boolean favorite;
    private boolean teasted;

    public DBBeer(String name, String description, String abv, String ibu, boolean isOrganic, boolean isRetired, String foodPairings, String icon, String image, String glass, String style, String breweries, String ingredients, boolean favorite, boolean teasted) {
        this.name = name;
        this.description = description;
        this.abv = abv;
        this.ibu = ibu;
        this.isOrganic = isOrganic;
        this.isRetired = isRetired;
        this.foodPairings = foodPairings;
        this.icon = icon;
        this.image = image;
        this.glass = glass;
        this.style = style;
        this.breweries = breweries;
        this.ingredients = ingredients;
        this.favorite = favorite;
        this.teasted = teasted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public boolean getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(boolean isOrganic) {
        this.isOrganic = isOrganic;
    }

    public boolean getIsRetired() {
        return isRetired;
    }

    public void setIsRetired(boolean isRetired) {
        this.isRetired = isRetired;
    }

    public String getFoodPairings() {
        return foodPairings;
    }

    public void setFoodPairings(String foodPairings) {
        this.foodPairings = foodPairings;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getBreweries() {
        return breweries;
    }

    public void setBreweries(String breweries) {
        this.breweries = breweries;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isTeasted() {
        return teasted;
    }

    public void setTeasted(boolean teasted) {
        this.teasted = teasted;
    }
}
