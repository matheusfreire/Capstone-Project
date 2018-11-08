package com.msf.myshops.model;

import java.util.List;

import lombok.Data;

@Data
public class Shop {
    private List<Item> itemList;


    public void addItemToShop(Item item){
        itemList.add(item);
    }

    public void removeItemFromShop(int position){
        itemList.remove(position);
    }
}
