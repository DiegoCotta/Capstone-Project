package com.example.beerlovers.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.beerlovers.model.DBBeer;

import java.util.List;

@Dao
public interface BeerDao {
    @Query("SELECT * FROM Beer WHERE tasted = 1")
    LiveData<List<DBBeer>> getTastedBeers();

    @Query("SELECT * FROM Beer WHERE favorite = 1")
    LiveData<List<DBBeer>> getFavoriteBeers();

    @Query("SELECT * FROM Beer WHERE stringId = :id")
    LiveData<DBBeer> getBeer(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBBeer beer);

    @Delete
    void deleteBeer(DBBeer beer);

}

