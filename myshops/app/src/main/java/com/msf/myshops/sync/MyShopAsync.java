package com.msf.myshops.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import com.google.firebase.database.DatabaseReference;
import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.AppExecutor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyShopAsync extends AsyncTask<Shop, Void, Boolean> {

    private Context mContext;
    private DatabaseReference mDatabaseReference;
    private Shop shop;

    public MyShopAsync(Context context, DatabaseReference databaseReference){
        this.mContext = context;
        this.mDatabaseReference = databaseReference;
    }

    @Override
    protected Boolean doInBackground(Shop... shops) {
        shop = shops[0];
        if(!shop.isFinalize()){
            String androidId = Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.ANDROID_ID);
            Map<String, Object> shopMap = new HashMap<>();
            shopMap.put("/shops/" + androidId+"/"+shop.getUid(), shop);
            mDatabaseReference.updateChildren(shopMap);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isFinalized) {
        super.onPostExecute(isFinalized);
        if(isFinalized){
            AppExecutor.getInstance().getDbIo().execute(() -> {
                MyShopDatabase database = MyShopDatabase.getInstance(mContext);
                shop.setFinalize(isFinalized);
                shop.setDate(new Date());
                database.getShopDao().update(shop);
            });
        }
    }
}
