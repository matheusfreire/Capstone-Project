package com.msf.myshops;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msf.myshops.model.Item;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NewItemFragment extends Fragment {

    private OnNewItemListener mListener;
    private Item mItem;

    @BindView(R.id.input_edit_descricao)
    TextInputEditText mInputEditDescription;

    @BindView(R.id.input_edit_valor_unitario)
    TextInputEditText mInputEditUnitValue;

    @BindView(R.id.input_edit_quantidade)
    TextInputEditText mInputEditQuantity;

    public NewItemFragment() {
        // Required empty public constructor
    }

    public static NewItemFragment newInstance(OnNewItemListener listener) {
        NewItemFragment fragment = new NewItemFragment();
        fragment.mListener = listener;
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewItemListener) {
            mListener = (OnNewItemListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnNewItemListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.btn_adicionar)
    public void addItem(View view){
        validateInputs();
        setValuesOnItem();
        if(mListener != null){
            mListener.onNewItemSave(mItem);
        }
    }

    private void setValuesOnItem() {
        mItem = new Item();
        mItem.setDescription(Objects.requireNonNull(mInputEditDescription.getText()).toString());
        mItem.setQuantity(Integer.valueOf(Objects.requireNonNull(mInputEditQuantity.getText()).toString()));
        mItem.setValue(Double.valueOf(Objects.requireNonNull(mInputEditUnitValue.getText()).toString()));
    }

    private void validateInputs() {
        if(inputIsEmpty(mInputEditDescription)){
            putErroMsgOnInputEdit(mInputEditDescription, getString(R.string.error_description));
            return;
        }
        if(inputIsEmpty(mInputEditUnitValue)){
            putErroMsgOnInputEdit(mInputEditUnitValue, getString(R.string.error_unit_value));
            return;
        }
        if(inputIsEmpty(mInputEditQuantity)){
            putErroMsgOnInputEdit(mInputEditQuantity, getString(R.string.error_quantity));
            return;
        }
    }

    private void putErroMsgOnInputEdit(TextInputEditText inputEditText, String msgError){
        inputEditText.setError(msgError);
    }

    private boolean inputIsEmpty(TextInputEditText inputEditText){
        return TextUtils.isEmpty(inputEditText.getText());
    }

    public interface OnNewItemListener {
        void onNewItemSave(Item item);
    }
}
