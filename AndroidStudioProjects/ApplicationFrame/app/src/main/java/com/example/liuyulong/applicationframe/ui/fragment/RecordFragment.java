package com.example.liuyulong.applicationframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.base.baseFragment.DisposableMvpFragment;
import com.example.liuyulong.applicationframe.databinding.FragmentRecordBinding;

import static android.databinding.DataBindingUtil.inflate;

public class RecordFragment extends DisposableMvpFragment {

    private FragmentRecordBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = inflate(inflater, R.layout.fragment_record, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
