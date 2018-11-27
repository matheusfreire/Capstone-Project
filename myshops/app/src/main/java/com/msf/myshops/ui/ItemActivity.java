package com.msf.myshops.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.Constants;
import com.msf.myshops.widget.SaveLastShopIntentService;

import java.io.Serializable;

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
        shop = getIntent().getParcelableExtra(Constants.SHOP.getKey());
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
        shop.addAmountForItem(item.getAmount());
        showNotification();
    }

    private void showNotification() {
        String total = Formatador.VALOR.formata(String.valueOf(shop.getTotal()));
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.shop_actual))
                .setContentText(getString(R.string.total_shop,total))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
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

}