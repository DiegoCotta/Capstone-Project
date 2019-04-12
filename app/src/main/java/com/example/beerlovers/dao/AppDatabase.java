package com.example.beerlovers.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.beerlovers.model.DBBeer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by diegocotta on 09/10/2018.
 */

@Database(entities = {DBBeer.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static AtomicBoolean isRunningTest;

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "beer-lovers";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                if (!isRunningTest())
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, AppDatabase.DATABASE_NAME)
                            .build();
                else {
                    sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                            AppDatabase.class)
                            .build();
                }
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract BeerDao beerDao();

    public static synchronized boolean isRunningTest() {
        if (null == isRunningTest) {
            boolean istest;

            try {
                Class.forName("android.support.test.espresso.Espresso");
                istest = true;
            } catch (ClassNotFoundException e) {
                istest = false;
            }

            isRunningTest = new AtomicBoolean(istest);
        }

        return isRunningTest.get();
    }
}


