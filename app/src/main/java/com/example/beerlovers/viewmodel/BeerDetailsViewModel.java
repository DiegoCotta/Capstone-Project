package com.example.beerlovers.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.beerlovers.dao.AppDatabase;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.DBBeer;
import com.example.beerlovers.utils.AppExecutors;
import com.google.firebase.analytics.FirebaseAnalytics;

public class BeerDetailsViewModel extends AndroidViewModel {

    private LiveData<DBBeer> dbBeer;
    private final AppDatabase database;
    private Beer beer;
    private FirebaseAnalytics mFirebaseAnalytics;

    public BeerDetailsViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(this.getApplication());
        dbBeer = new MutableLiveData<>();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(application);

    }

    public void getBeer(String id) {
        dbBeer = database.beerDao().getBeer(id);
    }

    public void changeFavorite() {
        Bundle bundle = new Bundle();

        if (dbBeer.getValue() == null) {
            final DBBeer b = beer.toDB();
            b.setFavorite(true);
            bundle.putString("beer", b.getName());
            mFirebaseAnalytics.logEvent("favorite_add", bundle);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.beerDao().insert(b);
                }
            });

        } else {
            dbBeer.getValue().setFavorite(!dbBeer.getValue().isFavorite());
            bundle.putString("beer", dbBeer.getValue().getName());
            if (!dbBeer.getValue().isFavorite())
                mFirebaseAnalytics.logEvent("favorite_add", bundle);
            else
                mFirebaseAnalytics.logEvent("favorite_remove", bundle);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.beerDao().insert(dbBeer.getValue());
                }
            });
        }

    }

    public void changeTasted() {
        Bundle bundle = new Bundle();

        if (dbBeer.getValue() == null) {
            final DBBeer b = beer.toDB();
            b.setTasted(true);
            bundle.putString("beer", b.getName());
            mFirebaseAnalytics.logEvent("tasted_add", bundle);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.beerDao().insert(b);
                }
            });
        } else {
            dbBeer.getValue().setTasted(!dbBeer.getValue().isTasted());
            if(!dbBeer.getValue().isTasted()){
                bundle.putString("beer", dbBeer.getValue().getName());
                mFirebaseAnalytics.logEvent("tasted_add", bundle);
            }else {
                bundle.putString("beer", dbBeer.getValue().getName());
                mFirebaseAnalytics.logEvent("tasted_remove", bundle);
            }
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.beerDao().insert(dbBeer.getValue());
                }
            });
        }
    }

    public LiveData<DBBeer> getDbBeer() {
        return dbBeer;
    }

    public void setDbBeer(DBBeer dbBeer) {
        ((MutableLiveData<DBBeer>) this.dbBeer).setValue(dbBeer);
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public void checkBeer() {
        if (dbBeer.getValue() != null && !dbBeer.getValue().isFavorite() && !dbBeer.getValue().isTasted()) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.beerDao().deleteBeer(dbBeer.getValue());
                }
            });
        }
    }
}
