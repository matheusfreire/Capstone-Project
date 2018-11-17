package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;

import java.util.ArrayList;
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

    private List<Item> mListItems;
    private Shop shop;
    private MyItemsRecyclerViewAdapter itemsRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ShopInterfaceListener mListener;

    public ItemsFragment(){

    }

    public static ItemsFragment newInstance(Shop shop, ShopInterfaceListener listener) {
        ItemsFragment fragment = new ItemsFragment();
        fragment.shop = shop;
        fragment.mListItems = shop.getItemList();
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mListItems != null){
            putItemsOnAdapter(mListItems);
        }
        setHasOptionsMenu(true);
    }

    private void putItemsOnAdapter(List<Item> listItems) {
        if (listItems != null && !listItems.isEmpty()) {
            initAdapter();
            mRecyclerViewItem.setAdapter(itemsRecyclerViewAdapter);
            showHideProgress(false);
        }
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
        putItemsOnAdapter(mListItems);
    }

    @Override
    public void showHideProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(!shop.isFinalize()){
            inflater.inflate(R.menu.menu_shop, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getFragmentManager().popBackStack();
            return true;
        } else if(id == R.id.finish_shop){
            mListener.onShopFinalize();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addItemOnAdapter(Shop shop, Item item){
        initList();
        mListItems.add(item);
        shop.addItemToShop(item);
        initAdapter();
    }

    private void initList() {
        if(mListItems == null){
            mListItems = new ArrayList<>();
        }
    }

    private void initAdapter() {
        if(itemsRecyclerViewAdapter == null){
            itemsRecyclerViewAdapter = new MyItemsRecyclerViewAdapter(mListItems);
        }
    }

    @OnClick(R.id.add_new_item)
    public void addNewItem(View view){
        ((ItemActivity) Objects.requireNonNull(getActivity())).addNewItem();
    }

    interface ShopInterfaceListener{
        void onShopFinalize();
    }

}
