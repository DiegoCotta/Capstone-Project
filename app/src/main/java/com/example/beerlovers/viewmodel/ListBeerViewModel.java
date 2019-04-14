package com.example.beerlovers.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.beerlovers.dao.AppDatabase;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.DBBeer;
import com.example.beerlovers.model.NetworkState;
import com.example.beerlovers.service.BeersDataSource;
import com.example.beerlovers.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ListBeerViewModel extends AndroidViewModel {

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Beer>> beersPagedListLiveData;
    private LiveData<List<DBBeer>> beersLiveData;
    BeersDataFactory feedDataFactory;
    PagedList.Config pagedListConfig;
    private final AppDatabase database;

    public ListBeerViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(this.getApplication());
        beersLiveData = new MutableLiveData<>();
        networkState = new MutableLiveData<>();
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    /*
     * Getter method for the pageList
     */
    public LiveData<PagedList<Beer>> getPagedListBeersLiveData() {
        return beersPagedListLiveData;

    }

    public void searchBeer(String textSearch) {
        feedDataFactory.setSearch(textSearch);
    }

    public void initPagedList() {
        executor = Executors.newFixedThreadPool(5);
        feedDataFactory = new BeersDataFactory();
        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
                new Function<BeersDataSource, LiveData<NetworkState>>() {
                    @Override
                    public LiveData<NetworkState> apply(BeersDataSource dataSource) {
                        return dataSource.getNetworkState();
                    }
                });

        pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPrefetchDistance(20)
                        .setPageSize(50)
                        .build();


        beersPagedListLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<List<DBBeer>> getFavoritesBeers() {
        beersLiveData = database.beerDao().getFavoriteBeers();
        return beersLiveData;
    }

    public LiveData<List<DBBeer>> getTastedBeers() {
        beersLiveData = database.beerDao().getTastedBeers();
        return beersLiveData;
    }

    public void updateBeer(final DBBeer beer) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.beerDao().insert(beer);
            }
        });
    }
}
