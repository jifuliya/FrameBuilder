package com.example.liuyulong.applicationframe.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.databinding.DialogTestBinding;

import static android.animation.ValueAnimator.ofFloat;
import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_BACK;

public class TestFragmentDialog extends MvpAppCompatDialogFragment {

    public static final String TEST = "TEST";

    private DialogTestBinding binding;

    public static void showSignInDialog(FragmentManager fm) {
        TestFragmentDialog dialog = new TestFragmentDialog();
        dialog.show(fm, TEST);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KEYCODE_BACK && event.getAction() == ACTION_UP) {
                    dismissAllowingStateLoss();
                }
                return false;
            }
        });

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
        }

        playAnimator();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_test, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void playAnimator(){
//        final AnimatorSet firstAnimator = new AnimatorSet();
//        firstAnimator.playTogether(createOpenTranslationIntoTabAnimator(), createOpenScaleSmallAnimator());
//        firstAnimator.start();

        createOpenScaleSmallAnimator().start();
    }

    @NonNull
    private ValueAnimator createOpenTranslationIntoTabAnimator() {

        ValueAnimator translation = ofFloat(-200, 0);
        translation.setInterpolator(new LinearInterpolator());
        translation.setDuration(400);
        translation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
                binding.top.setTranslationY(y);
            }
        });
        return translation;
    }

    @Nullable
    private ValueAnimator createOpenScaleSmallAnimator() {

        ValueAnimator translation = ofFloat(800, 0);
        translation.setInterpolator(new LinearInterpolator());
        translation.setDuration(400);
        translation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
                binding.bottom.setTranslationY(y);
            }
        });
        return translation;
    }
}
