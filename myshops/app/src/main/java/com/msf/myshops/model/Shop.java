package com.msf.myshops.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Shop implements Parcelable {
    private List<Item> itemList;
    private Date date;
    private double total;
    private boolean finalize;

    public Shop(){

    }

    protected Shop(Parcel in) {
        itemList = in.createTypedArrayList(Item.CREATOR);
        total = in.readDouble();
        finalize = in.readByte() != 0;
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

    public boolean isFinalize() {
        return finalize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(itemList);
        parcel.writeDouble(total);
        parcel.writeByte((byte) (finalize ? 1 : 0));
    }

    public void addItemToShop(Item item){
        if(itemList == null){
            itemList = new ArrayList<>();
        }
        itemList.add(item);
    }

    public void removeItemFromShop(int position){
        itemList.remove(position);
    }

}
