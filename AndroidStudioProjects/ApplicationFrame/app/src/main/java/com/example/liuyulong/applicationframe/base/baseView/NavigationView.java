package com.example.liuyulong.applicationframe.base.baseView;

import android.os.Bundle;

public interface NavigationView {

    void gotoActivity(Class<?> cls);

    void gotoActivity(Class<?> cls, boolean closeCurrent);

    void gotoActivity(Class<?> cls, boolean closeCurrent, Bundle data);
}
