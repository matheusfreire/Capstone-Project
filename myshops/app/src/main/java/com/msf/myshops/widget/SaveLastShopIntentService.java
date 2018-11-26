package com.msf.myshops.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.msf.myshops.BuildConfig;
import com.msf.myshops.R;
import com.msf.myshops.util.Constants;

public class SaveLastShopIntentService extends IntentService {


    public static final String ACTION_UPDATE_SHOP = "com.msf.myshops.action.update_last_shop";
    public static final String ACTION_UPDATE_WIDGET = "com.msf.myshops.action.update_widget";

    public SaveLastShopIntentService() {
        super(SaveLastShopIntentService.class.getSimpleName());
    }

    public static void updateMyShopWidget(Context context, String shopUid) {
        Intent intent = new Intent(context, SaveLastShopIntentService.class);
        intent.setAction(ACTION_UPDATE_SHOP);
        intent.putExtra(Constants.SHOP_PREFERENCES.getKey(), shopUid);
        context.startService(intent);
    }

    public static void startActionUpdateWidget(Context context) {
        Intent intent = new Intent(context, SaveLastShopIntentService.class);
        intent.setAction(ACTION_UPDATE_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_SHOP.equals(action)) {
                String shopUid = intent.getStringExtra(Constants.SHOP_PREFERENCES.getKey());
                handleUpdateText(shopUid);
            } else if (ACTION_UPDATE_WIDGET.equals(action)) {
                handleUpdateWidget();
            }
        }
    }

    private void handleUpdateText(String shopUid) {
        saveLastShop(shopUid);
        startActionUpdateWidget(this);
    }

    private void handleUpdateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, MyShopWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.last_shop_picked);
        MyShopWidgetProvider.updateMyShopWidget(this, appWidgetManager, appWidgetIds);
    }

    private void saveLastShop(String uid) {
        SharedPreferences sharedPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.SHOP_PREFERENCES.getKey(), uid);
        editor.apply();
    }
}
