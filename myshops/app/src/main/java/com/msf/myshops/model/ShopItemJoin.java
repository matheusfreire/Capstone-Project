package com.msf.myshops.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import lombok.Data;

@Entity(tableName = "shop_item_join",
        primaryKeys = { "itemUid", "shopUid" },
        foreignKeys = {
                @ForeignKey(entity = Shop.class,
                        parentColumns = "uid",
                        childColumns = "shopUid"),
                @ForeignKey(entity = Item.class,
                        parentColumns = "uid",
                        childColumns = "itemUid")
        })
@Data
public class ShopItemJoin {
    @NonNull
    private String shopUid, itemUid;

    public ShopItemJoin(@NonNull String shopUid, @NonNull String itemUid) {
        this.shopUid = shopUid;
        this.itemUid = itemUid;
    }
}
