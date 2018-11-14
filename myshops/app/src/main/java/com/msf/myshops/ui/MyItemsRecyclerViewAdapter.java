package com.msf.myshops.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyItemsRecyclerViewAdapter extends RecyclerView.Adapter<MyItemsRecyclerViewAdapter.ItemViewHolder> {

    private List<Item> mValues;

    MyItemsRecyclerViewAdapter() {
    }

    MyItemsRecyclerViewAdapter(List<Item> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        Item item = getItemByPosition(position);
        holder.mTextViewAmount.setText(String.valueOf(item.getAmount()));
        holder.mTextViewDescription.setText(item.getDescription());
        holder.mTextViewPrice.setText(String.valueOf(item.getValue()));
        holder.mTextViewQtde.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private Item getItemByPosition(int position){
        return mValues.get(position);
    }

    void addItem(Item item){
        if(mValues == null){
            mValues = new ArrayList<>();
        }
        mValues.add(item);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_description)
        TextView mTextViewDescription;
        @BindView(R.id.item_price)
        TextView mTextViewPrice;
        @BindView(R.id.item_qtde)
        TextView mTextViewQtde;
        @BindView(R.id.item_amount)
        TextView mTextViewAmount;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
