package com.example.beerlovers.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.beerlovers.R;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.NetworkState;
import com.example.beerlovers.service.BeersDataSource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ListBeerViewModel extends AndroidViewModel {

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Beer>> beersLiveData;
    BeersDataFactory feedDataFactory;
    PagedList.Config pagedListConfig;
    public ListBeerViewModel(@NonNull Application application) {

        super(application);
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


        beersLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    /*
     * Getter method for the pageList
     */
    public LiveData<PagedList<Beer>> getBeersLiveData() {

        return beersLiveData;

    }

    public void searchBeer(String textSearch) {
        feedDataFactory.setSearch(textSearch);
    }
}
