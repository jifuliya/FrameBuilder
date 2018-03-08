package com.example.liuyulong.applicationframe.base.baseActivity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.liuyulong.applicationframe.base.baseView.NavigationView;
import com.example.liuyulong.applicationframe.base.baseView.PromptView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.liuyulong.applicationframe.Utils.ToastUtils.showToast;

public class DisposableMvpActivity extends MvpAppCompatActivity
        implements PromptView, NavigationView {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void disposeOnDestroy(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void showPrompt(int promptRes) {
        showToast(this, promptRes);
    }

    @Override
    public void showPrompt(String prompt) {
        showToast(this, prompt);
    }

    @Override
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    @Override
    public void gotoActivity(Class<?> clz, boolean closeCurrent) {
        gotoActivity(clz, closeCurrent, null);
    }

    @Override
    public void gotoActivity(Class<?> clz, boolean closeCurrent, Bundle data) {
        Intent intent = new Intent(this, clz);
        if (data != null) intent.putExtras(data);
        startActivity(intent);
        if (closeCurrent) {
            finish();
        }
    }
}