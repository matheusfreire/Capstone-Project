package com.msf.myshops.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;
import com.msf.myshops.model.ShopItemJoin;

import java.util.List;
import java.util.UUID;

@Dao
public abstract class ShopDao {

    @Insert
    abstract void insert(Shop... shops);

    @Update
    public abstract void update(Shop... shops);

    @Delete
    abstract void delete(Shop... shops);

    @Query("SELECT * FROM shops")
    public abstract LiveData<List<Shop>> getShops();

    @Transaction
    public void insertShopAndItems(Shop shop, MyShopDatabase database){
        insert(shop);
        for(Item item : shop.getItemList()){
            if(item.getUid() == null){
                item.setUid(UUID.randomUUID().toString());
            }
            ShopItemJoin shopItemJoin = new ShopItemJoin(shop.getUid(), item.getUid());
            database.getItemDao().insert(item);
            database.getShopItemDao().insert(shopItemJoin);
        }
    }

    @Query("SELECT * FROM shops where uid = :shopUid")
    public abstract Cursor getShopByUid(String shopUid);

}