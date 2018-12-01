package com.msf.myshops.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.msf.myshops.R;
import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.AppExecutor;
import com.msf.myshops.util.Constants;
import com.msf.myshops.widget.SaveLastShopIntentService;

import java.util.Date;
import java.util.UUID;

import br.com.concrete.canarinho.formatador.Formatador;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity implements NewItemFragment.OnNewItemListener, ItemsFragment.ShopFinalizeListener{

    public static final String KEY_NEW_ITEM_FRAG = "NEW_ITEM_FRAGMENT";
    private ItemsFragment itemsFragment;

    @BindView(R.id.toolbar_item)
    Toolbar mToolbarItem;

    private Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbarItem);
        setTitleToolbar(getString(R.string.items));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        shop = getIntent().getParcelableExtra(Constants.SHOP.getKey());
//        createNotificationChannel();
    }

    public void setTitleToolbar(String title) {
        mToolbarItem.setTitle(title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (shop == null) {
            shop = new Shop();
        }
        itemsFragment = ItemsFragment.newInstance(shop, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.item_container, itemsFragment).commit();
    }

    @Override
    public void onNewItemSave(Item item) {
        setTitleToolbar(getString(R.string.items));
        itemsFragment.addItemOnAdapter(item);
        shop.addAmountForItem(item.getAmount());
        showNotification();
    }

    private void showNotification() {
        String total = Formatador.VALOR.formata(String.valueOf(shop.getTotal()));
        NotificationCompat.Builder builder =new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(getString(R.string.shop_actual))
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentText(getString(R.string.total_shop,total))
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel(builder, manager);
        manager.notify(0, builder.build());
    }

    private void createNotificationChannel(NotificationCompat.Builder builder, NotificationManager manager) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel nChannel = new NotificationChannel("0", Constants.NOTIFICATION_CHANNEL.getKey(), NotificationManager.IMPORTANCE_HIGH);
            nChannel.enableLights(true);
            nChannel.setLightColor(Color.BLUE);
            builder.setChannelId("0");
            manager.createNotificationChannel(nChannel);
        }
    }

    public void addNewItem() {
        NewItemFragment newItemFragment = NewItemFragment.newInstance(this);
        setTitleToolbar(getString(R.string.add_new_item));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.para_esquerda_entra, R.anim.para_esquerda_sai, R.anim.para_direita_entra, R.anim.para_direita_sai)
                .replace(R.id.item_container, newItemFragment).addToBackStack(KEY_NEW_ITEM_FRAG).commit();
    }

    @Override
    public void onShopFinalize(Shop shop) {
        SaveLastShopIntentService.updateMyShopWidget(this, shop.getUid());
        Intent data = new Intent();
        data.putExtra(Constants.SHOP.getKey(), shop);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
                showMessageDialog();
            } else {
                finish();
            }
            return true;
        }
        return true;
    }

    private void showMessageDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.alert);
        alertDialog.setMessage(getString(R.string.exit_new_fragment));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.yes), (dialogInterface, i) -> {
            getSupportFragmentManager().popBackStack();
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.no), (dialogInterface, i) -> {
            alertDialog.dismiss();
        });
        alertDialog.show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.para_direita_entra, R.anim.para_direita_sai);
    }
}