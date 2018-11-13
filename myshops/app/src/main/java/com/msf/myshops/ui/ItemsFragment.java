package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;

import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemsFragment extends Fragment {

    private final int COLUMN_COUNT = 1;

    private List<Item> mListItems;


    public ItemsFragment() {
    }

    public static ItemsFragment newInstance(List<Item> items) {
        ItemsFragment fragment = new ItemsFragment();
        fragment.mListItems = items;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void addItemOnAdapter(Item item){

    }

    @OnClick(R.id.add_new_item)
    public void addNewItem(View view){
        ((ItemActivity) Objects.requireNonNull(getActivity())).addNewItem();
    }

}
