package com.example.liuyulong.applicationframe.di.modules;

import com.example.liuyulong.applicationframe.BuildConfig;
import com.example.liuyulong.applicationframe.common.StringAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, SECONDS)
                .readTimeout(15, SECONDS)
                .writeTimeout(15, SECONDS)
                .addInterceptor(createHttpLoggingInterceptor())
                .build();
    }

    private HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(BuildConfig.DEBUG ? BASIC : NONE);
        return log;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(String.class, new StringAdapter().nullSafe())
                .create();
    }
}
