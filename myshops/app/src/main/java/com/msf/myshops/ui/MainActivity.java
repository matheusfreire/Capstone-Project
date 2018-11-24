package com.msf.myshops.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.msf.myshops.R;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.Constants;

public class MainActivity extends AppCompatActivity implements ShopsFragment.OnShopClickListener{

    private ShopsFragment shopsFragment;
    private final int REQCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shopsFragment = ShopsFragment.newInstance(this);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(data != null){
                Shop shop = data.getParcelableExtra(Constants.SHOP.getKey());
                shopsFragment.insertShop(shop);
            }
        }
    }

    public void newShop() {
        Intent intent = new Intent(this, ItemActivity.class);
        startActivityForResult(intent, REQCODE);
        overridePendingTransition(R.anim.para_esquerda_entra, R.anim.para_esquerda_sai);
    }
}