package com.example.liuyulong.applicationframe.base.basePresenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposablePresenter<View extends MvpView> extends MvpPresenter<View> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void disposeOnDestroy(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void manualClear() {
        compositeDisposable.clear();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
