package com.example.liuyulong.applicationframe.ui.header;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.databinding.ViewHolderHeaderRefreshBinding;
import com.example.liuyulong.applicationframe.ui.fragment.TestFragmentDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static android.databinding.DataBindingUtil.inflate;
import static android.view.LayoutInflater.from;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class RecyclerRefreshHeader implements RecyclerArrayAdapter.ItemView {

    ViewHolderHeaderRefreshBinding binding;
    Context context;

    @Override
    public View onCreateView(ViewGroup parent) {
        context = parent.getContext();
        binding = inflate(from(context), R.layout.view_holder_header_refresh, parent, false);
        return binding.getRoot();
    }

    @Override
    public void onBindView(View headerView) {
    }
}
