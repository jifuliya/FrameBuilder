package com.example.liuyulong.applicationframe.Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuyulong.applicationframe.R;

import static android.text.TextUtils.isEmpty;
import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.LayoutInflater.from;
import static android.widget.Toast.LENGTH_SHORT;
import static com.example.liuyulong.applicationframe.Utils.CommonUtils.Dp2Px;

public class ToastUtils {

    public static void showToast(Context context, int resourceId) {
        showToast(context, context.getString(resourceId));
    }

    public static void showToast(Context context, @Nullable CharSequence text) {
        if (isEmpty(text))
            return;
        Toast toast = new Toast(context);
        toast.setDuration(LENGTH_SHORT);
        View view = from(context).inflate(R.layout.layout_toast, null);
        ((TextView) view.findViewById(android.R.id.message)).setText(text);
        toast.setView(view);
        toast.setGravity(BOTTOM | CENTER_HORIZONTAL, 0, Dp2Px(context, 50));
        toast.show();
    }
}
