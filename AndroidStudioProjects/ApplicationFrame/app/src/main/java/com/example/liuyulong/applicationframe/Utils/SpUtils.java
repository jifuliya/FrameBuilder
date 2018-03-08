package com.example.liuyulong.applicationframe.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.example.liuyulong.applicationframe.Constants.SETTINGS_FILE_NAME;

public class SpUtils {

    public static <T> T get(Context context, String key, T defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        T value = (T) sp.getAll().get(key);
        return value != null ? value : defValue;
    }

    public static <T> T get(SharedPreferences sp, String key, T defValue) {
        T value = (T) sp.getAll().get(key);
        return value != null ? value : defValue;
    }

    public static void setStr(SharedPreferences sp, String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static void setStr(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static void setLong(SharedPreferences sp, String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    public static void setInt(SharedPreferences sp, String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static void setBoolean(SharedPreferences sp, String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
