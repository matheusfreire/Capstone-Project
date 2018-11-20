package com.msf.myshops.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ItemViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    public ItemViewModelFactory(@NonNull Application application) {
        super(application);
    }
}
