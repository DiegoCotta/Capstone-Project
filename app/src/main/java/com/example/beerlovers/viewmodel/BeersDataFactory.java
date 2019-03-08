package com.example.beerlovers.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.beerlovers.service.BeersDataSource;

public class BeersDataFactory extends DataSource.Factory {
    private MutableLiveData<BeersDataSource> mutableLiveData;
    private BeersDataSource beersDataSource;

    public BeersDataFactory() {
        this.mutableLiveData = new MutableLiveData();
    }

    @Override
    public DataSource create() {
        beersDataSource = new BeersDataSource();
        mutableLiveData.postValue(beersDataSource);
        return beersDataSource;
    }

    public MutableLiveData<BeersDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setSearch(String search) {
        beersDataSource.setSearch(search);
    }
}
