package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "Ingredients")
public class Ingredients {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    List<Ingredient> hops;
    List<Ingredient> yeast;
    List<Ingredient> malt;

    public List<Ingredient> getHops() {
        return hops;
    }

    public void setHops(List<Ingredient> hops) {
        this.hops = hops;
    }

    public List<Ingredient> getYeast() {
        return yeast;
    }

    public void setYeast(List<Ingredient> yeast) {
        this.yeast = yeast;
    }

    public List<Ingredient> getMalt() {
        return malt;
    }

    public void setMalt(List<Ingredient> malt) {
        this.malt = malt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
