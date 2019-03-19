package com.example.beerlovers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "Ingredients")
public class Ingredients implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    List<Ingredient> hops;
    List<Ingredient> yeast;
    List<Ingredient> malt;

    protected Ingredients(Parcel in) {
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

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
