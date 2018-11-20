package com.msf.myshops.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.msf.myshops.model.Shop;

public class ShopViewModel extends AndroidViewModel{

    private LiveData<Shop> shopLiveData;

    public ShopViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Shop> getShopLiveData() {
        return shopLiveData;
    }
}
