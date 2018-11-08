package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.msf.myshops.R;

public class MainActivity extends AppCompatActivity {

    private ShopsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

}
