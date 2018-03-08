package com.example.liuyulong.applicationframe.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.liuyulong.applicationframe.FrameApplication;
import com.example.liuyulong.applicationframe.base.basePresenter.DisposablePresenter;
import com.example.liuyulong.applicationframe.mvp.views.MainView;

@InjectViewState
public class MainPresenter extends DisposablePresenter<MainView> {

    private static final String TAG = "MainPresenter";

    public MainPresenter() {
        FrameApplication.getAppComponent().inject(this);
    }

    public void changeTextView() {


    }

}
