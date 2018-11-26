package com.msf.myshops.widget;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.msf.myshops.BuildConfig;
import com.msf.myshops.R;
import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.AppExecutor;
import com.msf.myshops.util.Constants;

import br.com.concrete.canarinho.formatador.Formatador;

public class MyShopWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyShopViewFactory(this.getApplicationContext());
    }
}


class MyShopViewFactory implements RemoteViewsService.RemoteViewsFactory, LifecycleOwner {

    private Context mContext;
    private Shop shop;
    private MyShopDatabase database;
    private LifecycleRegistry mLifecycleRegistry;
    private Cursor mCursor;

    private void getFromDb(){
        SharedPreferences preferences = mContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        final String shopUid = preferences.getString(Constants.SHOP_PREFERENCES.getKey(), "");
        if (shopUid != null && !shopUid.isEmpty()) {
            AppExecutor.getInstance().getDbIo().execute(() -> mCursor = database.getShopDao().getShopByUid(shopUid));
        }
    }

    MyShopViewFactory(Context mContext) {
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        this.mContext = mContext;
        database = MyShopDatabase.getInstance(mContext);
        getFromDb();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (database == null || mCursor == null) {
            return;
        }
        if  (mCursor.moveToFirst()) {
            do {
                shop = new Shop(mCursor);
            }while (mCursor.moveToNext());
        }
    }

    @Override
    public void onDestroy() {
        database.close();
        shop = null;
    }

    @Override
    public int getCount() {
        return shop != null ? 1 : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (shop == null) {
            return null;
        }

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.my_shop_widget);
        String total = Formatador.VALOR.formata(String.valueOf(shop.getTotal()));
        views.setTextViewText(R.id.appwidget_text, mContext.getString(R.string.last_shop,total)) ;
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
