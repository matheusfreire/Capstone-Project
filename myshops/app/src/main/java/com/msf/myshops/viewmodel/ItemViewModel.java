package com.msf.myshops.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Item;

public class ItemViewModel extends ViewModel{

    private LiveData<Item> itemsLiveData;

    public ItemViewModel(MyShopDatabase database, String shopHashId) {
//        task = database.taskDao().loadTaskById(taskId);
    }


    public LiveData<Item> getItemsLiveData() {
        return itemsLiveData;
    }
}
