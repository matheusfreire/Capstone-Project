package com.msf.myshops.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Item;

import java.util.List;

import lombok.Getter;

public class ItemViewModel extends ViewModel{

    @Getter
    private LiveData<List<Item>> itemsLiveData;

    public ItemViewModel(MyShopDatabase database, String shopHashId) {
        itemsLiveData = database.getShopItemDao().getItemsFromShops(shopHashId);
    }

}