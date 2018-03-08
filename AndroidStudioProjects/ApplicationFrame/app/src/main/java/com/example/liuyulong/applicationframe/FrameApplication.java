package com.example.liuyulong.applicationframe;

import android.support.multidex.MultiDexApplication;

import com.bumptech.glide.Glide;
import com.example.liuyulong.applicationframe.di.AppComponent;
import com.example.liuyulong.applicationframe.di.DaggerAppComponent;
import com.example.liuyulong.applicationframe.di.modules.ApiModule;
import com.example.liuyulong.applicationframe.di.modules.ContextModule;
import com.example.liuyulong.applicationframe.di.modules.RetrofitModule;

import java.io.InterruptedIOException;
import java.net.SocketException;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

import static android.util.Log.e;
import static com.example.liuyulong.applicationframe.BuildConfig.DEBUG;

public class FrameApplication extends MultiDexApplication {

    private static final String TAG = "FrameApplication";

    private static AppComponent appComponent;

    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof InterruptedException) {
                    if (DEBUG) {
                        e(TAG, "InterruptedException", throwable);
                    }
                } else if (throwable instanceof InterruptedIOException) {
                    if (DEBUG) {
                        e(TAG, "InterruptedIOException", throwable);
                    }
                } else if (throwable instanceof SocketException) {
                    if (DEBUG) {
                        e(TAG, "SocketException", throwable);
                    }
                }
            }
        });
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .apiModule(new ApiModule())
                .retrofitModule(new RetrofitModule())
                .build();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_MODERATE:
                Glide.get(this).clearMemory();
                break;
        }
    }
}
