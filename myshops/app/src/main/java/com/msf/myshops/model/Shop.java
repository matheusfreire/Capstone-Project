package com.msf.myshops.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Shop implements Parcelable {
    private List<Item> itemList;
    private Date date;
    private double total;

    protected Shop(Parcel in) {
        itemList = in.createTypedArrayList(Item.CREATOR);
        total = in.readDouble();
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public void addItemToShop(Item item){
        itemList.add(item);
    }

    public void removeItemFromShop(int position){
        itemList.remove(position);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(itemList);
        parcel.writeDouble(total);
    }
}
