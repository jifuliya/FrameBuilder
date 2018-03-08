package com.example.liuyulong.applicationframe.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.base.baseActivity.DisposableMvpActivity;
import com.example.liuyulong.applicationframe.databinding.ActivitySplashBinding;
import com.example.liuyulong.applicationframe.mvp.presenters.SplashPresenter;
import com.example.liuyulong.applicationframe.mvp.views.SplashView;
import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.functions.Consumer;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SplashActivity extends DisposableMvpActivity implements SplashView {

    @InjectPresenter
    SplashPresenter splashPresenter;

    private static final String TAG = "SplashActivity";

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        splashPresenter.splashLoading();
        setupPassBtn();
    }

    @Override
    public void onUpdateCountDown(Long mLong) {
        long a = 6 - mLong;
        binding.text.setText("跳过" + a);
    }

    @Override
    public void gotoMain() {
        MainActivity.startMainActivity(SplashActivity.this);
    }

    private void setupPassBtn() {
        RxView.clicks(binding.text)
                .debounce(100, MILLISECONDS)
                .observeOn(mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        gotoMain();
                    }
                });
    }
}
