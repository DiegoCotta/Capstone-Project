package com.example.beerlovers.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.Breweries;
import com.example.beerlovers.model.Category;
import com.example.beerlovers.model.Country;
import com.example.beerlovers.model.Glass;
import com.example.beerlovers.model.Images;
import com.example.beerlovers.model.Ingredient;
import com.example.beerlovers.model.Ingredients;
import com.example.beerlovers.model.Labels;
import com.example.beerlovers.model.Location;
import com.example.beerlovers.model.Srm;
import com.example.beerlovers.model.Style;


/**
 * Created by diegocotta on 09/10/2018.
 */

@Database(entities = {Beer.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "beer-lovers";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

}


