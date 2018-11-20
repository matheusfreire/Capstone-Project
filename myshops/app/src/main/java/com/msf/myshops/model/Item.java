package com.msf.myshops.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
@Entity(tableName = "items")
public class Item implements Parcelable{

    @PrimaryKey
    private String description;

    private double value;

    private int quantity;

    private double amount;

    @Ignore
    public Item(){

    }

    public Item(String description, double value, int quantity, double amount) {
        this.description = description;
        this.value = value;
        this.quantity = quantity;
        this.amount = amount;
    }

    @Ignore
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
