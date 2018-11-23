package com.msf.myshops.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.msf.myshops.model.Item;
import com.msf.myshops.model.ShopItemJoin;

import java.util.List;

@Dao
public interface ShopItemDao {
    @Insert
    void insert(ShopItemJoin shopItemJoin);

    @Query("SELECT * FROM items i INNER JOIN shop_item_join sij ON " +
           "i.uid=sij.itemUid WHERE sij.shopUid=:shopUid")
    LiveData<List<Item>> getItemsFromShops(final String shopUid);

}