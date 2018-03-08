package com.example.liuyulong.applicationframe.di.modules;

import android.content.SharedPreferences;

import com.example.liuyulong.applicationframe.common.ApiManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {ContextModule.class, RetrofitModule.class})
public class ApiModule {

    @Provides
    @Singleton
    public ApiManager provideApiManager(Retrofit.Builder builder, SharedPreferences sp) {
        return ApiManager.getInstance(builder, sp);
    }
}
