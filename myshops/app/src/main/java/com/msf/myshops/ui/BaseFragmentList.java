package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.msf.myshops.util.ViewSetup;

public abstract class BaseFragmentList extends Fragment implements ViewSetup {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setupRecycler();
        this.showHideProgress(true);
    }
}
