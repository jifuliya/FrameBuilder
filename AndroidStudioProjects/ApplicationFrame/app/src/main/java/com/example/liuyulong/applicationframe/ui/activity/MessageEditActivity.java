package com.example.liuyulong.applicationframe.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.liuyulong.applicationframe.R;
import com.example.liuyulong.applicationframe.databinding.ActivityMessageEditBinding;

public class MessageEditActivity extends MvpAppCompatActivity {

    private ActivityMessageEditBinding binding;
    private String title;

    public final static String TAG = "MessageEditActivity";

    public static void startMessageEditActivity(Context context, String title) {
        Intent intent = new Intent(context, MessageEditActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message_edit);

        setupToolbar();
        handleIntent();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent == null) {
            return;
        }

        title = intent.getStringExtra("title");
        binding.toolTitle.setText(title);
    }

    private void handleIntent() {
        Intent intent = getIntent();

        if (intent == null) {
            return;
        }

        title = intent.getStringExtra("title");
        Log.i(TAG, "MessageEditActivity" + title);
        binding.toolTitle.setText(title);
    }
}
