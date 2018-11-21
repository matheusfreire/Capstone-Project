package com.msf.myshops.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.msf.myshops.db.MyShopDatabase;

public class ItemViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private MyShopDatabase myShopDatabase;
    private String shopUid;

    public ItemViewModelFactory(MyShopDatabase myShopDatabase, String shopUid) {
        this.myShopDatabase = myShopDatabase;
        this.shopUid = shopUid;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ItemViewModel(this.myShopDatabase, this.shopUid);
    }
}
