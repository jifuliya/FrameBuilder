package com.example.liuyulong.applicationframe.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface SplashView extends MvpView{

    void onUpdateCountDown(Long mLong);

    void gotoMain();
}
