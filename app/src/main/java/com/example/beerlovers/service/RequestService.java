package com.example.beerlovers.service;

import com.example.beerlovers.BuildConfig;
import com.example.beerlovers.model.BaseResponse;
import com.example.beerlovers.model.Beer;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by diegocotta on 05/03/2019.
 */
//Reference https://zeroturnaround.com/rebellabs/getting-started-with-retrofit-2/
public interface RequestService {


    @GET("beers?withBreweries=Y&withIngredients=Y")
    @Headers("HTTP_ACCEPT: application/json")
    Call<BaseResponse<List<Beer>>> getBeers(@Query("key") String key, @Query("p") int page);

    @GET("beer/random?withBreweries=Y&withIngredients=Y&hasLabels=Y")
    @Headers("HTTP_ACCEPT: application/json")
    Call<BaseResponse<Beer>> getRandomBeer(@Query("key") String key);

    @GET("search?withBreweries=Y&withIngredients=Y")
    @Headers("HTTP_ACCEPT: application/json")
    Call<BaseResponse<List<Beer>>> searchBeer(@Query("type") String type, @Query("q") String q, @Query("key") String key, @Query("p") int page);

    OkHttpClient okHttp = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new StethoInterceptor())
            .build();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.SERVICE_URL)

            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
            .client(okHttp)
            .build();


}

