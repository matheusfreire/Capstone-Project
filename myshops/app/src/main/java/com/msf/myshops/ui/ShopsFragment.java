package com.msf.myshops.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msf.myshops.R;
import com.msf.myshops.model.Shop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopsFragment extends Fragment {

    private final int COLUMN_COUNT = 1;
    private OnShopClickListener mListener;

    @BindView(R.id.recycler_view_shop)
    RecyclerView mRecyclerViewShop;

    @BindView(R.id.error_message)
    TextView mErroMsg;

    @BindView(R.id.progress_shop)
    ProgressBar mProgress;

    private LinearLayoutManager linearLayoutManager;

    public ShopsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        ButterKnife.bind(this, view);
        showHideProgress(true);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewShop.setLayoutManager(linearLayoutManager);
        mRecyclerViewShop.setHasFixedSize(true);
    }

    private void showHideProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE:View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnShopClickListener) {
            mListener = (OnShopClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnItemClickListener");
        }
    }

    @OnClick(R.id.add_new_shop)
    public void addNewBuy(View view){
        Intent intent = new Intent(getContext(), ItemActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.para_esquerda_entra, R.anim.para_esquerda_sai);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnShopClickListener {
        void addClickOnShop(Shop shop);
    }
}
