package com.msf.myshops.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.msf.myshops.model.Shop;

import java.util.List;

@Dao
public interface ShopDao {

    @Insert
    void insert(Shop... shops);

    @Update
    void update(Shop... shops);

    @Delete
    void delete(Shop... shops);

    @Query("SELECT * FROM shops")
    LiveData<List<Shop>> getShops();

}