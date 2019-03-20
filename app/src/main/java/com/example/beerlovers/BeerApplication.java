package com.example.beerlovers;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class BeerApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
