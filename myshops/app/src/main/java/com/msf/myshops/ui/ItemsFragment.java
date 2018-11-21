package com.msf.myshops.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.msf.myshops.R;
import com.msf.myshops.db.MyShopDatabase;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;
import com.msf.myshops.viewmodel.ItemViewModel;
import com.msf.myshops.viewmodel.ItemViewModelFactory;

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

    private Shop shop;
    private ItemViewModel itemViewModel;
    private ShopFinalize mListenerFinalize;

    public ItemsFragment(){

    }

    public static ItemsFragment newInstance(Shop shop, ShopFinalize listenerFinalize) {
        ItemsFragment fragment = new ItemsFragment();
        fragment.shop = shop;
        fragment.mListenerFinalize = listenerFinalize;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        ItemViewModelFactory factory = new ItemViewModelFactory(MyShopDatabase.getInstance(getContext()), shop.getUid());
        itemViewModel = ViewModelProviders.of(this, factory).get(ItemViewModel.class);
        return view;
    }

    private void showNoItemAdd() {
        mErrorView.setText(getString(R.string.no_items_add));
        mRecyclerViewItem.setVisibility(View.INVISIBLE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewItem.setLayoutManager(linearLayoutManager);
        mRecyclerViewItem.setHasFixedSize(true);
    }

    @Override
    public void showHideProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void observableFromVm() {
        if(itemViewModel != null){
           itemViewModel.getItemsLiveData().observe(this, this::buildRecyclerOrErroView);
        }
    }
    private void buildRecyclerOrErroView(@Nullable List<Item> items){
        if(items == null || items.isEmpty()){
            showNoItemAdd();
        } else {
            buildAdapter(items);
        }
        showHideProgress(false);
    }

    private void buildAdapter(List<Item> items) {
        MyItemsRecyclerViewAdapter itemsRecyclerViewAdapter = new MyItemsRecyclerViewAdapter(items);
        mRecyclerViewItem.setAdapter(itemsRecyclerViewAdapter);
        mRecyclerViewItem.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
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
            shop.setItemList(itemViewModel.getItemsLiveData().getValue());
            mListenerFinalize.onShopFinalize(shop);
        }
        return true;
    }

    public void addItemOnAdapter(Item item){
        itemViewModel.getItemsLiveData().getValue().add(item);
    }


    @OnClick(R.id.add_new_item)
    public void addNewItem(View view){
        ((ItemActivity) Objects.requireNonNull(getActivity())).addNewItem();
    }

    interface ShopFinalize {
        void onShopFinalize(Shop shop);
    }

}
