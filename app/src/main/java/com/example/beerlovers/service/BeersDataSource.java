package com.example.beerlovers.service;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.beerlovers.BuildConfig;
import com.example.beerlovers.model.BaseResponse;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.NetworkState;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://proandroiddev.com/8-steps-to-implement-paging-library-in-android-d02500f7fffe
public class BeersDataSource extends PageKeyedDataSource<Integer, Beer> {

    private MutableLiveData networkState;
    private MutableLiveData initialLoading;
    private static String search;
    private int numPages = 50;

    public BeersDataSource() {
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Beer> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        if (search == null || search.isEmpty())
            RequestService.retrofit.create(RequestService.class).getBeers(BuildConfig.API_KEY, 1).enqueue(new Callback<BaseResponse<List<Beer>>>() {
                @Override
                public void onResponse(Call<BaseResponse<List<Beer>>> call, Response<BaseResponse<List<Beer>>> response) {
                    if (response.isSuccessful() && (response.body() != null)) {
                        if (response.body().getData() != null) {
                            callback.onResult(response.body().getData(), null, response.body().getNumberOfPages() >= 1 ? 2 : null);
                        } else
                            callback.onResult(new ArrayList<Beer>(), null, 0);
                        initialLoading.postValue(NetworkState.LOADED);
                        networkState.postValue(NetworkState.LOADED);
                    } else {
                        initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<List<Beer>>> call, Throwable t) {
                    setError(t);
                }
            });
        else
            RequestService.retrofit.create(RequestService.class).searchBeer("beer", search, BuildConfig.API_KEY, 0).enqueue(new Callback<BaseResponse<List<Beer>>>() {
                @Override
                public void onResponse(Call<BaseResponse<List<Beer>>> call, Response<BaseResponse<List<Beer>>> response) {
                    if (response.isSuccessful() && (response.body() != null)) {
                        if (response.body().getData() != null) {
                            callback.onResult(response.body().getData(), null, response.body().getNumberOfPages() >= 1 ? 2 : null);
                        } else
                            callback.onResult(new ArrayList<Beer>(), null, null);
                        initialLoading.postValue(NetworkState.LOADED);
                        networkState.postValue(NetworkState.LOADED);
                    } else {
                        initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<List<Beer>>> call, Throwable t) {
                    setError(t);
                }
            });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Beer> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Beer> callback) {
        if (search == null ||
                search.isEmpty())
            RequestService.retrofit.create(RequestService.class).getBeers(BuildConfig.API_KEY, params.key).enqueue(new Callback<BaseResponse<List<Beer>>>() {
                @Override
                public void onResponse(Call<BaseResponse<List<Beer>>> call, Response<BaseResponse<List<Beer>>> response) {
                    if (response.isSuccessful()) {
                        Integer nextKey = (params.key == response.body().getTotalResults()) ? null : params.key + 1;
                        if (response.body().getData() != null)
                            callback.onResult(response.body().getData(), nextKey);
                        else
                            callback.onResult(new ArrayList<Beer>(), nextKey);
                        networkState.postValue(NetworkState.LOADED);

                    } else
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }

                @Override
                public void onFailure(Call<BaseResponse<List<Beer>>> call, Throwable t) {
                    setError(t);
                }
            });
        else
            RequestService.retrofit.create(RequestService.class).searchBeer("beer", search, BuildConfig.API_KEY, params.key).enqueue(new Callback<BaseResponse<List<Beer>>>() {
                @Override
                public void onResponse(Call<BaseResponse<List<Beer>>> call, Response<BaseResponse<List<Beer>>> response) {
                    if (response.isSuccessful()) {
                        Integer nextKey = (params.key == response.body().getTotalResults()) ? null : params.key + 1;
                        if (response.body().getData() != null)
                            callback.onResult(response.body().getData(), nextKey);
                        else
                            callback.onResult(new ArrayList<Beer>(), nextKey);
                        networkState.postValue(NetworkState.LOADED);

                    } else
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }

                @Override
                public void onFailure(Call<BaseResponse<List<Beer>>> call, Throwable t) {
                    setError(t);
                }
            });
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    public void setError(Throwable t) {
        String errorMessage = t == null ? "error" : t.getMessage();
        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
    }

    public void setSearch(String search) {
        this.search = search;
        this.invalidate();
    }
}
