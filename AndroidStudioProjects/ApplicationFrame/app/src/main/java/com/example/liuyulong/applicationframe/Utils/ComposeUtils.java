package com.example.liuyulong.applicationframe.Utils;

import android.content.Context;
import android.content.Intent;

import com.example.liuyulong.applicationframe.mvp.models.autovalue.ErrorResponse;
import com.example.liuyulong.applicationframe.ui.activity.MainActivity;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import static android.util.Log.w;
import static com.example.liuyulong.applicationframe.BuildConfig.DEBUG;
import static com.example.liuyulong.applicationframe.FrameApplication.getAppComponent;
import static com.example.liuyulong.applicationframe.Utils.ToastUtils.showToast;

public class ComposeUtils {
    private static final String TAG = "ComposeUtils";

    public static <T> ObservableTransformer<T, T> processError() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof CompositeException) {
                            CompositeException compositeException = (CompositeException) throwable;
                            List<Throwable> throwableList = compositeException.getExceptions();
                            for (Throwable item : throwableList) {
                                processThrowable(item);
                            }
                        } else {
                            processThrowable(throwable);
                        }
                    }
                });
            }
        };
    }

    private static void processThrowable(Throwable throwable) throws IOException {
        if (DEBUG) {
            w(TAG, throwable);
            showToast(getAppComponent().getContext(), throwable.getMessage());
        }

        if (throwable instanceof HttpException) {
            processHttpException((HttpException) throwable);
        } else {
        }
    }

    private static void processHttpException(HttpException throwable) throws IOException {
        if (throwable.response().code() == 401) {
            processUserTokenExpireException();
        }

        ResponseBody responseBody = throwable.response().errorBody();
        if (responseBody == null) {
            showToast(getAppComponent().getContext(), throwable.message());
        }
        ErrorResponse errorResponse = getAppComponent().getGson().
                fromJson(responseBody.source().readUtf8(),
                        ErrorResponse.class);
        showToast(getAppComponent().getContext(), errorResponse.error().message());
    }

    private static void processUserTokenExpireException() {
        Context context = getAppComponent().getContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}