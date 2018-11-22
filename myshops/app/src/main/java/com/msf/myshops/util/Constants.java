package com.msf.myshops.util;

public enum Constants {

    SHOP("SHOP"),
    NEW_SHOP_IMPL("NEW_SHOP_IMPL"),
    PATTERN_DATE_BR("dd/MM/yyyy");

    private String key;
    Constants(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
