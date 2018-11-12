package com.msf.myshops.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemsFragment extends Fragment {

    private final int COLUMN_COUNT = 1;

    private OnItemClickListener mListener;


    public ItemsFragment() {
    }

    public static ItemsFragment newInstance(OnItemClickListener onItemClickListener) {
        ItemsFragment fragment = new ItemsFragment();
        fragment.mListener = onItemClickListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()+ " must implement OnItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.add_new_item)
    public void addNewBuy(View view){

    }

    public interface OnItemClickListener {
        void onItemInteraction(Item item);
    }
}
