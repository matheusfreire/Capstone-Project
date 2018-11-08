package com.msf.myshops.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Item implements Parcelable{

    private String description;

    private double value;

    private int quantity;

    private double amount;

    protected Item(Parcel in) {
        description = in.readString();
        value = in.readDouble();
        quantity = in.readInt();
        amount = in.readDouble();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeDouble(value);
        parcel.writeInt(quantity);
        parcel.writeDouble(amount);
    }
}
