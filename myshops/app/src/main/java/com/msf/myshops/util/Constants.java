package com.msf.myshops.util;

public enum Constants {

    SHOP("SHOP"),
    NEW_SHOP_IMPL("NEW_SHOP_IMPL"),
    PATTERN_DATE_BR("dd/MM/yyyy"),
    SHOP_PREFERENCES("UID_SHOP"),
    NOTIFICATION_CHANNEL("MY_SHOP"),
    KEY_NEW_ITEM_FRAG("NEW_ITEM_FRAGMENT"),
    KEY_UNIQUE("users"),
    CHANNEL_ID("0");

    private String key;
    Constants(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
