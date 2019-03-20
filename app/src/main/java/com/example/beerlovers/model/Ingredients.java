package com.example.beerlovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class Ingredients implements Parcelable {
    int id;
    List<Ingredient> hops;
    List<Ingredient> yeast;
    List<Ingredient> malt;

    protected Ingredients(Parcel in) {
        id = in.readInt();
        hops = in.createTypedArrayList(Ingredient.CREATOR);
        yeast = in.createTypedArrayList(Ingredient.CREATOR);
        malt = in.createTypedArrayList(Ingredient.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeTypedList(hops);
        dest.writeTypedList(yeast);
        dest.writeTypedList(malt);
    }


    @Override
    public String toString() {
        StringBuilder ingredients = new StringBuilder();
        if (getHops() != null) {
            ingredients.append("Hops:");
            for (Ingredient ingredient : getHops()) {
                ingredients.append(" ").append(ingredient.getName()).append(",");
            }
            ingredients = new StringBuilder(ingredients.toString().replaceFirst(".$", "\n"));
        }
        if (getMalt() != null) {
            ingredients.append("Malt:");
            for (Ingredient ingredient : getMalt()) {
                ingredients.append(" ").append(ingredient.getName()).append(",");
            }
            ingredients = new StringBuilder(ingredients.toString().replaceFirst(".$", "\n"));
        }
        if (getYeast() != null) {
            ingredients.append("Yeast:");
            for (Ingredient ingredient : getYeast()) {
                ingredients.append(" ").append(ingredient.getName()).append(",");
            }
        }
        ingredients = new StringBuilder(ingredients.toString().replaceFirst(".$", ""));
        return ingredients.toString();
    }
}
