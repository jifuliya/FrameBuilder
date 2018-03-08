package com.example.liuyulong.applicationframe.base.baseActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.base.baseView.DataListView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public abstract class BaseRecyclerViewActivity<T> extends DisposableMvpActivity
        implements DataListView<T> {

    protected RecyclerArrayAdapter<T> adapter;
    protected EasyRecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = providerAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initRecyclerView();
    }

    @Override
    public void onDataLoadSuccess(@NonNull List<? extends T> data, boolean isRefresh) {
        if (isRefresh) {
            adapter.clear();
        }
        adapter.addAll(data);
    }

    @Override
    public void onDataLoadFailed(boolean isRefresh) {
        adapter.pauseMore();
        if (recyclerView.getSwipeToRefresh().isRefreshing()) {
            recyclerView.getSwipeToRefresh().setRefreshing(false);
        }

        if (isRefresh && adapter.getCount() == 0) {
            recyclerView.showError();
        }
    }

    protected abstract RecyclerArrayAdapter<T> providerAdapter();

    protected abstract EasyRecyclerView providerRecyclerView();

    protected abstract RecyclerView.LayoutManager providerLayoutManager();

    protected abstract void refresh();

    protected abstract void loadMore();

    private void initRecyclerView() {
        recyclerView = providerRecyclerView();
        recyclerView.getSwipeToRefresh().setColorSchemeResources(R.color.colorPrimary);
        recyclerView.setLayoutManager(providerLayoutManager());

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        recyclerView.setAdapterWithProgress(adapter);

        adapter.setNoMore(R.layout.recycler_view_no_more);
        adapter.setMore(R.layout.recycler_view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                loadMore();
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setError(R.layout.recycler_view_load_more_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {

            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
    }
}