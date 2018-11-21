package com.msf.myshops.ui;

import android.content.Context;
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
    private Context context;

    MyItemsRecyclerViewAdapter(List<Item> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        Item item = getItemByPosition(position);
        holder.mTextViewDescription.setText(item.getDescription());
        holder.mTextViewAmount.setText(context.getString(R.string.price_tag,String.valueOf(item.getAmount())));
        holder.mTextViewPrice.setText(context.getString(R.string.price_item,String.valueOf(item.getValue())));
        holder.mTextViewQtde.setText(context.getString(R.string.quantity_tag,String.valueOf(item.getQuantity())));
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
