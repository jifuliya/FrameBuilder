package com.example.liuyulong.applicationframe.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.liuyulong.applicationframe.ui.viewholder.ScaleViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class ScaleAdapter extends RecyclerArrayAdapter<Integer> {


    public ScaleAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScaleViewHolder(parent);
    }
}
