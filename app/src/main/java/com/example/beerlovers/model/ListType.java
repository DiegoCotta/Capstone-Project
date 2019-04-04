package com.example.beerlovers.model;

import java.io.Serializable;

public enum ListType implements Serializable {
    NETWORK("List Beers"),
    FAVORITE("Favorite Beers"),
    TASTED("Tasted Beers");

    private final String name;

    private ListType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
