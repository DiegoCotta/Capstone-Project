package com.example.beerlovers;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;

public class BeerApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        MobileAds.initialize(this, getString(R.string.AD_KEY));

    }
}
