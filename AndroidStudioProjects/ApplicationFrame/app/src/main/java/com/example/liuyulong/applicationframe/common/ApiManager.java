package com.example.liuyulong.applicationframe.common;

import android.content.SharedPreferences;

import com.example.liuyulong.applicationframe.Utils.SpUtils;

import retrofit2.Retrofit;

import static com.example.liuyulong.applicationframe.Key.API_DOMAIN;

public class ApiManager {
    private volatile static ApiManager instance;
    private Retrofit.Builder builder;
    private SharedPreferences sp;

    private Api api;

    private ApiManager(Retrofit.Builder builder, SharedPreferences sp) {
        this.builder = builder;
        this.sp = sp;
        setBaseUrl(SpUtils.get(sp, API_DOMAIN, "https://shoplex-api.betaqsk.com/"));
    }

    public static ApiManager getInstance(Retrofit.Builder builder, SharedPreferences sp) {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager(builder, sp);
                }
            }
        }
        return instance;
    }

    public void setBaseUrl(String baseUrl) {
        Retrofit retrofit = builder.baseUrl(baseUrl).build();
        api = retrofit.create(Api.class);
    }

    public Api get() {
        return api;
    }
}
