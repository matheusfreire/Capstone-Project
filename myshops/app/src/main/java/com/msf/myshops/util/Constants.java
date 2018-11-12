package com.msf.myshops.util;

public enum Constants {

    SHOP("SHOP");

    private String key;
    Constants(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
