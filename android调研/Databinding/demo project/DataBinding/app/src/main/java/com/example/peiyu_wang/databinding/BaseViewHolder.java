package com.example.peiyu_wang.databinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T dataBing;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public T getDataBing() {
        return dataBing;
    }

    public void setDataBing(T dataBing) {
        this.dataBing = dataBing;
    }
}