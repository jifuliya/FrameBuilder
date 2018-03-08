package com.example.liuyulong.applicationframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.databinding.FragmentImportantBinding;

import static android.databinding.DataBindingUtil.inflate;

public class ImportantRecordFragment extends MvpAppCompatFragment {

    private FragmentImportantBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = inflate(inflater, R.layout.fragment_important, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
