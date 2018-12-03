package com.msf.myshops.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import com.google.firebase.database.DatabaseReference;
import com.msf.myshops.model.Shop;

import java.util.HashMap;
import java.util.Map;

public class MyShopAsync extends AsyncTask<Shop, Void, Void> {

    private Context mContext;
    private DatabaseReference mDatabaseReference;

    public MyShopAsync(Context context, DatabaseReference databaseReference){
        this.mContext = context;
        this.mDatabaseReference = databaseReference;
    }

    @Override
    protected Void doInBackground(Shop... shops) {
        Shop shop = shops[0];
        if(!shop.isFinalize()){
            String androidId = Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.ANDROID_ID);
            Map<String, Object> shopMap = new HashMap<>();
            shopMap.put("/shops/" + androidId+"/"+shop.getUid(), shop);
            mDatabaseReference.updateChildren(shopMap);
        }
        return null;
    }
}
