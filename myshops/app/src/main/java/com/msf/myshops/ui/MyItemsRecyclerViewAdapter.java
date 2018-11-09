package com.msf.myshops.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;
import com.msf.myshops.ui.ItemsFragment.OnItemClickListener;

import java.util.List;

import butterknife.ButterKnife;

public class MyItemsRecyclerViewAdapter extends RecyclerView.Adapter<MyItemsRecyclerViewAdapter.ItemViewHolder> {

    private final List<Item> mValues;
    private final OnItemClickListener mListener;

    public MyItemsRecyclerViewAdapter(List<Item> items, ItemsFragment.OnItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private Item getItemByPosition(int position){
        return mValues.get(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final View mView;
        Item mItem;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }

        @Override
        public void onClick(View view) {
            mListener.onItemInteraction(getItemByPosition(getAdapterPosition()));
        }
    }
}
