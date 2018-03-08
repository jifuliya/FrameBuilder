package com.example.liuyulong.applicationframe.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.base.baseActivity.DisposableMvpActivity;
import com.example.liuyulong.applicationframe.databinding.ActivityMainBinding;
import com.example.liuyulong.applicationframe.ui.adapter.ScaleAdapter;
import com.example.liuyulong.applicationframe.ui.fragment.TestFragmentDialog;
import com.example.liuyulong.applicationframe.ui.header.RecyclerRefreshHeader;
import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.functions.Consumer;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MainActivity extends DisposableMvpActivity {

    private ActivityMainBinding binding;
    private ScaleAdapter adapter;
    private int mScreenWidth;
    private static final float MIN_SCALE = 1f;
    private static final float MAX_SCALE = 1.3f;
    private int mMinWidth;
    private int mMaxWidth;

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupRecyclerView();
        setupData();

        RxView.clicks(binding.click)
                .debounce(100, MILLISECONDS)
                .observeOn(mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        TestFragmentDialog.showSignInDialog(getSupportFragmentManager());
                    }
                });
    }

    private void setupData() {
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mMinWidth = (int) (mScreenWidth * 0.28f);
        mMaxWidth = mScreenWidth - 2 * mMinWidth;
        String[] names = new String[100];
    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 1, 1, 1, 1, 1, 1, 1,};
        adapter.addAll(integers);
        adapter.addHeader(new RecyclerRefreshHeader());
    }

    private void setupRecyclerView() {
        adapter = new ScaleAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.getRecyclerView().scrollToPosition(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.removeAllHeader();
    }
}
