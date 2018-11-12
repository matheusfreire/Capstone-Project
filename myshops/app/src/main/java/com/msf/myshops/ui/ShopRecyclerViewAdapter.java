package com.msf.myshops.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msf.myshops.R;
import com.msf.myshops.model.Shop;
import com.msf.myshops.ui.ShopsFragment.OnShopClickListener;

import java.util.List;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ViewHolder> {

    private final List<Shop> mValues = null;
    private final OnShopClickListener mListener;

    public ShopRecyclerViewAdapter(OnShopClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private Shop getShopByPosition(int position){
        return mValues.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final View mView;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.addClickOnShop(getShopByPosition(getAdapterPosition()));
        }
    }
}
