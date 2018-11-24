//package com.msf.myshops.ui;
//
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.msf.myshops.R;
//import com.msf.myshops.model.Shop;
//import com.msf.myshops.viewmodel.ShopViewModel;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class ShopsFragment extends BaseFragmentList{
//
//    private OnShopClickListener mListener;
//
//    @BindView(R.id.recycler_view_shop)
//    RecyclerView mRecyclerViewShop;
//
//    @BindView(R.id.error_message)
//    TextView mErroMsg;
//
//    @BindView(R.id.progress_shop)
//    ProgressBar mProgress;
//
//    private ShopViewModel shopViewModel;
//
//    public ShopsFragment() {
//
//    }
//
//    public static ShopsFragment newInstance(OnShopClickListener listener) {
//        ShopsFragment fragment = new ShopsFragment();
//        fragment.mListener = listener;
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
//        ButterKnife.bind(this, view);
//        shopViewModel = ViewModelProviders.of(this).get(ShopViewModel.class);
//        return view;
//    }
//
//    @Override
//    public void setupRecycler() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerViewShop.setLayoutManager(linearLayoutManager);
//        mRecyclerViewShop.setHasFixedSize(true);
//    }
//
//    @Override
//    public void showHideProgress(boolean show) {
//        mProgress.setVisibility(show ? View.VISIBLE:View.GONE);
//    }
//
//    @Override
//    public void observableFromVm() {
//        shopViewModel.getShopLiveData().observe(this, this::buildRecyclerOrErroView);
//    }
//
//    private void buildRecyclerOrErroView(@Nullable List<Shop> shops){
//        if(shops == null || shops.isEmpty()){
//            buildEmptyMsg();
//        } else {
//            buildAdapter(shops);
//        }
//        showHideProgress(false);
//    }
//
//    private void buildEmptyMsg() {
//        mErroMsg.setText(getString(R.string.no_shops));
//        mRecyclerViewShop.setVisibility(View.INVISIBLE);
//        mErroMsg.setVisibility(View.VISIBLE);
//    }
//
//    private void buildAdapter(List<Shop> shops) {
//        ShopRecyclerViewAdapter shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(mListener, shops);
//        mRecyclerViewShop.setAdapter(shopRecyclerViewAdapter);
//        mRecyclerViewShop.setVisibility(View.VISIBLE);
//        mErroMsg.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnShopClickListener) {
//            mListener = (OnShopClickListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnItemClickListener");
//        }
//    }
//
//    @OnClick(R.id.add_new_shop)
//    public void addNewShop(View view){
//        ((MainActivity) getActivity()).newShop();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    public void insertShop(Shop shop) {
//        shopViewModel.getShopLiveData().getValue().add(shop);
//    }
//
//    public interface OnShopClickListener {
//        void addClickOnShop(Shop shop);
//    }
//}
