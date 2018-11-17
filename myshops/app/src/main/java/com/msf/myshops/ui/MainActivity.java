package com.msf.myshops.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.msf.myshops.R;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.Constants;

public class MainActivity extends AppCompatActivity implements ShopsFragment.OnShopClickListener, ItemActivity.ShopListener{

    private ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shopsFragment = new ShopsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, shopsFragment).commit();
    }

    @Override
    public void addClickOnShop(Shop shop) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(Constants.SHOP.getKey(),shop);
        startActivity(intent);
        overridePendingTransition(R.anim.para_esquerda_entra, R.anim.para_esquerda_sai);
    }

    @Override
    public void onFinalize(Shop shop) {
       shopsFragment.addShopToRecycler(shop);
    }
}
