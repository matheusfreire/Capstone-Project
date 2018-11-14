package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemsFragment extends BaseFragmentList {

    @BindView(R.id.recycler_view_item)
    RecyclerView mRecyclerViewItem;

    @BindView(R.id.error_message_item)
    TextView mErrorView;

    @BindView(R.id.progress_loading)
    ProgressBar mProgress;

    private final int COLUMN_COUNT = 1;

    private List<Item> mListItems;
    private MyItemsRecyclerViewAdapter itemsRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

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
        if(mListItems != null){
            putItemsOnAdapter(mListItems);
        }
    }

    private void putItemsOnAdapter(List<Item> listItems) {
        if (listItems != null && !listItems.isEmpty()) {
            if(itemsRecyclerViewAdapter == null){
                itemsRecyclerViewAdapter = new MyItemsRecyclerViewAdapter(mListItems);
                mRecyclerViewItem.setAdapter(itemsRecyclerViewAdapter);
            }
        }
        showHideProgress(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewItem.setLayoutManager(linearLayoutManager);
        mRecyclerViewItem.setHasFixedSize(true);
    }

    @Override
    public void showHideProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE:View.GONE);
    }

    public void addItemOnAdapter(Shop shop, Item item){
        if(shop == null){
            shop = new Shop();
        }
        shop.addItemToShop(item);
        if(itemsRecyclerViewAdapter == null){
            itemsRecyclerViewAdapter = new MyItemsRecyclerViewAdapter();
        }
        itemsRecyclerViewAdapter.addItem(item);
    }

    @OnClick(R.id.add_new_item)
    public void addNewItem(View view){
        ((ItemActivity) Objects.requireNonNull(getActivity())).addNewItem();
    }

}
