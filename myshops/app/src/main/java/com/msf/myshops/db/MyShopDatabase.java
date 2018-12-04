package com.msf.myshops.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.msf.myshops.dao.ItemDao;
import com.msf.myshops.dao.ShopDao;
import com.msf.myshops.dao.ShopItemDao;

import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;
import com.msf.myshops.model.ShopItemJoin;

@TypeConverters(DateConverter.class)
@Database(entities = { Shop.class, Item.class, ShopItemJoin.class },version = 1, exportSchema = false)
public abstract class MyShopDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "myshops";
    private static MyShopDatabase sInstance;

    public static MyShopDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MyShopDatabase.class, MyShopDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract ShopDao getShopDao();
    public abstract ItemDao getItemDao();
    public abstract ShopItemDao getShopItemDao();
}
