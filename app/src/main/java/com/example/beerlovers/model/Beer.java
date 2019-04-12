package com.example.beerlovers.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;


public class Beer implements Parcelable {
    private String id;
    private String name;
    private String nameDisplay;
    private String description;
    private String abv;
    private String ibu;
    private String isOrganic;
    private String isRetired;
    private String foodPairings;
    private Labels labels;
    private Glass glass;
    private Style style;
    private List<Breweries> breweries;
    private Ingredients ingredients;

    protected Beer(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameDisplay = in.readString();
        description = in.readString();
        abv = in.readString();
        ibu = in.readString();
        isOrganic = in.readString();
        isRetired = in.readString();
        foodPairings = in.readString();
        labels = in.readParcelable(Labels.class.getClassLoader());
        glass = in.readParcelable(Glass.class.getClassLoader());
        style = in.readParcelable(Style.class.getClassLoader());
        breweries = in.createTypedArrayList(Breweries.CREATOR);
        ingredients = in.readParcelable(Ingredients.class.getClassLoader());
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

    public Beer() {
    }

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

    public String getIsOrganic() {
        return isOrganic;
    }

    public String getIsRetired() {
        return isRetired;
    }

    public Labels getLabels() {
        return labels;
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


    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public void setIsRetired(String isRetired) {
        this.isRetired = isRetired;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }


    public List<Breweries> getBreweries() {
        return breweries;
    }

    public String getBreweriesString() {
        StringBuilder breweries = new StringBuilder();
        for (Breweries brs : getBreweries()) {
            breweries.append(brs.getNameShortDisplay()).append(",");
        }
        breweries = new StringBuilder(breweries.toString().replaceFirst(".$", ""));
        return breweries.toString();
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

    public String getFoodPairings() {
        return foodPairings;
    }

    public void setFoodPairings(String foodPairings) {
        this.foodPairings = foodPairings;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
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
        dest.writeString(isOrganic);
        dest.writeString(isRetired);
        dest.writeString(foodPairings);
        dest.writeParcelable(labels, flags);
        dest.writeParcelable(glass, flags);
        dest.writeParcelable(style, flags);
        dest.writeTypedList(breweries);
        dest.writeParcelable(ingredients, flags);
    }


    public DBBeer toDB() {
        String ibu = "";
        if (getIbu() != null) {
            ibu = getIbu();
        } else if (getStyle() != null && getStyle().getIbuMin() != null) {
            ibu = getStyle().getIbuMin();
        }

        String abv = "";
        if (getAbv() != null) {
            abv = getAbv();
        } else if (getStyle() != null && getStyle().getAbvMin() != null) {
            abv = getStyle().getAbvMin();
        }
        String icon = "";
        String image = "";

        if (getLabels() != null) {
            if (getLabels().getIcon() != null) {
                icon = getLabels().getIcon();
            }
            if (getLabels().getLarge() != null) {
                image = getLabels().getLarge();
            } else if (getLabels().getMedium() != null) {
                image = getLabels().getMedium();
            }
        }
        return new DBBeer(
                getId(),
                this.nameDisplay,
                this.description,
                abv,
                ibu,
                isOrganic.equalsIgnoreCase("Y"),
                isRetired.equalsIgnoreCase("Y"),
                foodPairings,
                icon,
                image,
                glass != null ? glass.getName() : null,
                style != null ? style.getShortName() : null,
                getBreweries() != null ? getBreweriesString() : null,
                getIngredients() != null ? getIngredients().toString() : null,
                false,
                false);
    }
}