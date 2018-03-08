package com.example.liuyulong.applicationframe.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;
import static com.example.liuyulong.applicationframe.Constants.SETTINGS_FILE_NAME;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }


    @Provides
    @Singleton
    public SharedPreferences provideSp() {
        return context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
    }
}