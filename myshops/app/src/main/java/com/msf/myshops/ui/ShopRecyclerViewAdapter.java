package com.msf.myshops.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msf.myshops.R;
import com.msf.myshops.model.Shop;
import com.msf.myshops.ui.ShopsFragment.OnShopClickListener;
import com.msf.myshops.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.concrete.canarinho.formatador.Formatador;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ViewHolder> implements ShopsFragment.OnShopClickListener{

    private List<Shop> mValues;
    private final OnShopClickListener mListener;
    private Context context;

    ShopRecyclerViewAdapter(OnShopClickListener listener, List<Shop> shops) {
        mListener = listener;
        mValues = shops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Shop shop = getShopByPosition(position);
        String total = Formatador.VALOR.formata(String.valueOf(shop.getTotal()));
        holder.mTotal.setText(context.getString(R.string.total_shop,total));
        holder.mQtdeItens.setText(context.getString(R.string.qtde_itens, String.valueOf(shop.getTotalItems())));
        holder.mTextViewDate.setText(formatStringDate(shop.getDate()));
    }

    private String formatStringDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.PATTERN_DATE_BR.getKey(), new Locale("pt", "BR"));
        return simpleDateFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private Shop getShopByPosition(int position){
        return mValues.get(position);
    }

    void addItem(Shop shop) {
        mValues.add(shop);
        notifyDataSetChanged();
    }

    @Override
    public void addClickOnShop(Shop shop) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final View mView;

        @BindView(R.id.date_shop)
        TextView mTextViewDate;

        @BindView(R.id.total)
        TextView mTotal;

        @BindView(R.id.qtde_items)
        TextView mQtdeItens;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.addClickOnShop(getShopByPosition(getAdapterPosition()));
        }
    }
}
