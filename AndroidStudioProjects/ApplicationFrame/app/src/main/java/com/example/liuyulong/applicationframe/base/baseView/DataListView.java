package com.example.liuyulong.applicationframe.base.baseView;

import android.support.annotation.NonNull;

import java.util.List;

public interface DataListView<T> {

    void onDataLoadSuccess(List<T> data, Boolean isRefresh);

    void onDataLoadFailed(Boolean isRefresh);

    void onDataLoadSuccess(@NonNull List<? extends T> data, boolean isRefresh);

    void onDataLoadFailed(boolean isRefresh);
}
