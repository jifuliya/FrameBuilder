package com.example.liuyulong.applicationframe.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.liuyulong.applicationframe.FrameApplication;
import com.example.liuyulong.applicationframe.base.basePresenter.DisposablePresenter;
import com.example.liuyulong.applicationframe.mvp.views.SplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Observable.interval;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

@InjectViewState
public class SplashPresenter extends DisposablePresenter<SplashView> {

    private static final String TAG = "MainPresenter";

    public SplashPresenter() {
        FrameApplication.getAppComponent().inject(this);
    }

    public void splashLoading(){
        Disposable disposable = interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long mLong) throws Exception {
                        getViewState().onUpdateCountDown(mLong);
                    }
                }).filter(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong > 5;
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getViewState().gotoMain();
                    }
                });
        disposeOnDestroy(disposable);
    }
}
