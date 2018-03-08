package com.example.liuyulong.applicationframe.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.liuyulong.applicationframe.di.modules.ApiModule;
import com.example.liuyulong.applicationframe.di.modules.ContextModule;
import com.example.liuyulong.applicationframe.mvp.presenters.MainPresenter;
import com.example.liuyulong.applicationframe.mvp.presenters.SplashPresenter;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ApiModule.class})
public interface
AppComponent {

    Context getContext();

    SharedPreferences getSp();

    Gson getGson();

    void inject(MainPresenter mainPresenter);

    void inject(SplashPresenter splashPresenter);

}
