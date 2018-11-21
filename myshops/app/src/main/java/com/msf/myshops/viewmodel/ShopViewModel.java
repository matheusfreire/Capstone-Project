package com.msf.myshops.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Shop;

import java.util.List;

import lombok.Getter;

public class ShopViewModel extends AndroidViewModel{

    @Getter
    private LiveData<List<Shop>> shopLiveData;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        MyShopDatabase database = MyShopDatabase.getInstance(this.getApplication());
        shopLiveData = database.getShopDao().getShops();
    }


}
