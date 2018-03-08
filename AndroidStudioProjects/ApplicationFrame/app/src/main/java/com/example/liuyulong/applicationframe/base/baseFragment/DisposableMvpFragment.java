package com.example.liuyulong.applicationframe.base.baseFragment;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.liuyulong.applicationframe.base.baseView.NavigationView;
import com.example.liuyulong.applicationframe.base.baseView.PromptView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.liuyulong.applicationframe.Utils.ToastUtils.showToast;

public class DisposableMvpFragment extends MvpAppCompatFragment
        implements PromptView, NavigationView {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void disposeOnDestroy(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void showPrompt(int promptRes) {
        showToast(getContext(), promptRes);
    }

    @Override
    public void showPrompt(String prompt) {
        showToast(getContext(), prompt);
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
        Intent intent = new Intent(getActivity(), clz);
        if (data != null) intent.putExtras(data);
        startActivity(intent);
        if (closeCurrent) {
            getActivity().finish();
        }
    }
}
