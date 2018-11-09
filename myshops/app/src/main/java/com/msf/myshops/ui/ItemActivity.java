package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.msf.myshops.NewItemFragment;
import com.msf.myshops.R;
import com.msf.myshops.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity implements NewItemFragment.OnNewItemListener {

    private ItemsFragment itemsFragment;
    private NewItemFragment newItemFragment;

    @BindView(R.id.toolbar_item)
    Toolbar mToolbarItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbarItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, itemsFragment).commit();
    }

    @Override
    public void onNewItemSave(Item item) {

    }
}
