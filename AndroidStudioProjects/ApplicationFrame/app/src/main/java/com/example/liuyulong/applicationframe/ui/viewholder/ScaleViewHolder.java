package com.example.liuyulong.applicationframe.ui.viewholder;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.databinding.ViewHolderItemScaleBinding;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class ScaleViewHolder extends BaseViewHolder<Integer> {
    private ViewHolderItemScaleBinding binding;
    private int width;

    public ScaleViewHolder(ViewGroup parent) {
        super(parent, R.layout.view_holder_item_scale);
        binding = DataBindingUtil.bind(itemView);

        setupItemWidth();
    }

    private void setupItemWidth() {
        Resources resources = getContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        binding.getRoot().getLayoutParams().width = width / 5;
        binding.imageView.getLayoutParams().width = width / 5 - 10;
        binding.imageView.getLayoutParams().height = (width / 5 - 10) / 2;
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
    }
}
