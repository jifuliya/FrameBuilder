package com.example.liuyulong.applicationframe.Utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.liuyulong.applicationframe.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.disposables.Disposable;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static java.io.File.separator;
import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.util.regex.Pattern.compile;

public class CommonUtils {
    private static final Pattern NICK_NAME_PATTERN = compile("^[a-zA-Z0-9\\-\\_]{4,20}");
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_FORMAT_WITH_MS = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_FORMAT_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String DATE_TIME_FORMAT_ZERO_MS = "HH:mm";
    private static final long MILLPERSECONDS = 1000;
    private static final long SECONDSPERMIN = 60;
    private static final long MINPERHOUR = 60;

    public static void hideSoftInputMethod(View view, Context context) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive())
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static long getFileFolderSize(File file) {
        long size = 0;
        if (file == null || !file.exists()) return size;
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                size += getFileFolderSize(subFile);
            }
        } else {
            size = file.length();
        }
        return size;
    }

    public static boolean deleteDir(File dir) {
        if (dir == null || !dir.exists()) return true;
        if (dir.isDirectory()) {
            for (File subFile : dir.listFiles()) {
                if (!deleteDir(subFile)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static String getDate(long mills) {
        return convertToDateTimeString(mills, DATE_TIME_FORMAT_WITH_MS);
    }

    public static String getZeroDate(long mills) {
        return convertToDateTimeString(mills, DATE_TIME_FORMAT_ZERO_MS);
    }

    public static String getDateUTC(long mills) {
        return convertToDateTimeStringUTC(mills, DATE_TIME_FORMAT);
    }

    public static String getDateUTCMs(long mills) {
        return convertToDateTimeStringUTC(mills, DATE_TIME_FORMAT_MS);
    }

    public static String getDateMs(long mills) {
        return convertToDateTimeString(mills, DATE_TIME_FORMAT_MS);
    }

    public static String getDateShort(long mills) {
        return convertToDateTimeString(mills, DATE_TIME_FORMAT);
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, R.drawable.ic_launcher_background);
    }

    public static void loadImage(Fragment fragment, String url, ImageView imageView) {
        loadImage(fragment, url, imageView, R.drawable.ic_launcher_background);
    }

    public static void loadAvatar(final Fragment fragment, String url, final ImageView imageView) {
        Glide.with(fragment)
                .load(url).asBitmap()
                .placeholder(R.drawable.ic_launcher_background)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        final RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        roundedBitmapDrawable.setCircular(true);

                        getView().setImageDrawable(roundedBitmapDrawable);
                    }
                });
    }

    public static void loadAvatar(final Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url).asBitmap()
                .placeholder(R.drawable.ic_launcher_background)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        final RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        roundedBitmapDrawable.setCircular(true);

                        getView().setImageDrawable(roundedBitmapDrawable);
                    }
                });
    }


    public static void loadImage(Context context, String url, ImageView imageView, int placeHolderRes) {
        Glide.with(context).load(url)
                .crossFade()
                .dontTransform()
                .dontAnimate()
                .placeholder(placeHolderRes)
                .into(imageView);

    }

    public static void loadVerificationImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .crossFade()
                .dontTransform()
                .dontAnimate()
                .into(imageView);

    }

    public static void loadImage(Fragment fragment, String url, ImageView imageView, int placeHolderRes) {
        Glide.with(fragment).load(url)
                .crossFade()
                .centerCrop()
                .dontTransform()
                .dontAnimate()
                .placeholder(placeHolderRes)
                .into(imageView);
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static Snackbar showSnackbar(View root, CharSequence message, int duration, CharSequence actionTextName, View.OnClickListener actionOnClickListener) {
        Snackbar snackbar = Snackbar.make(root, message, duration);

        snackbar.setAction(actionTextName, actionOnClickListener);
        Button actionButton = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
        actionButton.setBackgroundResource(0);
        actionButton.setTextColor(root.getResources().getColor(R.color.colorPrimary));

        snackbar.show();
        return snackbar;
    }

    public static void setupCountDown(final TextView minute0,
                                      final TextView minute1,
                                      final TextView second0,
                                      final TextView second1,
                                      final TextView tenMills0,
                                      final TextView tenMills1, long millsUtilFinished) {
        if (millsUtilFinished == 0) {
            minute0.setText(valueOf(0));
            minute1.setText(valueOf(0));
            second0.setText(valueOf(0));
            second1.setText(valueOf(0));
            tenMills0.setText(valueOf(0));
            tenMills1.setText(valueOf(0));
            return;
        }


        long minutes = millsUtilFinished / MILLPERSECONDS / SECONDSPERMIN;

        minute0.setText(valueOf(minutes % 100 / 10));
        minute1.setText(valueOf(minutes % 10));

        long seconds = millsUtilFinished / MILLPERSECONDS - minutes * SECONDSPERMIN;

        second0.setText(valueOf(seconds % 100 / 10));
        second1.setText(valueOf(seconds % 10));

        long tenMills = (millsUtilFinished - minutes * SECONDSPERMIN * MILLPERSECONDS - seconds * MILLPERSECONDS) / 10;

        tenMills0.setText(valueOf(tenMills % 100 / 10));
        tenMills1.setText(valueOf(tenMills % 10));
    }

    public static void setupCountDownWithMin(final TextView minute0,
                                             final TextView minute1,
                                             final TextView second0,
                                             final TextView second1,
                                             final TextView tenMills0,
                                             final TextView tenMills1,
                                             final TextView minuteSecondsDeliver,
                                             final TextView secondsTenMillsDeliver, long millsUtilFinished) {
        if (millsUtilFinished == 0) {
            minute0.setText(valueOf(0));
            minute1.setText(valueOf(0));
            second0.setText(valueOf(0));
            second1.setText(valueOf(0));
            tenMills0.setText(valueOf(0));
            tenMills1.setText(valueOf(0));
            return;
        }

        long hours = millsUtilFinished / MILLPERSECONDS / SECONDSPERMIN / MINPERHOUR;

        minute0.setText(valueOf(hours % 100 / 10));
        minute1.setText(valueOf(hours % 10));

        long minutes = millsUtilFinished / MILLPERSECONDS / SECONDSPERMIN - hours * MINPERHOUR;

        second0.setText(valueOf(minutes % 100 / 10));
        second1.setText(valueOf(minutes % 10));

        long seconds = millsUtilFinished / MILLPERSECONDS - minutes * SECONDSPERMIN;

        tenMills0.setText(valueOf(seconds % 100 / 10));
        tenMills1.setText(valueOf(seconds % 10));

        if ((hours % 100 / 10) == 0 && (hours % 10) == 0 && (minutes % 100 / 10) == 0 && (minutes % 10) < 5) {
            minute0.setTextColor(Color.parseColor("#ed0c0c"));
            minute1.setTextColor(Color.parseColor("#ed0c0c"));
            second0.setTextColor(Color.parseColor("#ed0c0c"));
            second1.setTextColor(Color.parseColor("#ed0c0c"));
            tenMills0.setTextColor(Color.parseColor("#ed0c0c"));
            tenMills1.setTextColor(Color.parseColor("#ed0c0c"));
            minuteSecondsDeliver.setTextColor(Color.parseColor("#ed0c0c"));
            secondsTenMillsDeliver.setTextColor(Color.parseColor("#ed0c0c"));
        } else {
            minute0.setTextColor(Color.parseColor("#fe7f19"));
            minute1.setTextColor(Color.parseColor("#fe7f19"));
            second0.setTextColor(Color.parseColor("#fe7f19"));
            second1.setTextColor(Color.parseColor("#fe7f19"));
            tenMills0.setTextColor(Color.parseColor("#fe7f19"));
            tenMills1.setTextColor(Color.parseColor("#fe7f19"));
            minuteSecondsDeliver.setTextColor(Color.parseColor("#fe7f19"));
            secondsTenMillsDeliver.setTextColor(Color.parseColor("#fe7f19"));
        }

    }

    public static void setupProgressCountTimerToZero(final TextView minute0,
                                                     final TextView minute1,
                                                     final TextView second0,
                                                     final TextView second1,
                                                     final TextView tenMills0,
                                                     final TextView tenMills1) {
        minute0.setText(valueOf(0));
        minute1.setText(valueOf(0));
        second0.setText(valueOf(0));
        second1.setText(valueOf(0));
        tenMills0.setText(valueOf(0));
        tenMills1.setText(valueOf(0));
    }

    public static void setupCountDownWithMinByProgress(final TextView minute0,
                                                       final TextView minute1,
                                                       final TextView second0,
                                                       final TextView second1,
                                                       final TextView tenMills0,
                                                       final TextView tenMills1,
                                                       long millsUtilFinished) {
        if (millsUtilFinished == 0) {
            minute0.setText(valueOf(0));
            minute1.setText(valueOf(0));
            second0.setText(valueOf(0));
            second1.setText(valueOf(0));
            tenMills0.setText(valueOf(0));
            tenMills1.setText(valueOf(0));
            return;
        }

        long hours = millsUtilFinished / MILLPERSECONDS / SECONDSPERMIN / MINPERHOUR;

        minute0.setText(valueOf(hours % 100 / 10));
        minute1.setText(valueOf(hours % 10));

        long minutes = millsUtilFinished / MILLPERSECONDS / SECONDSPERMIN - hours * MINPERHOUR;

        second0.setText(valueOf(minutes % 100 / 10));
        second1.setText(valueOf(minutes % 10));

        long seconds = millsUtilFinished / MILLPERSECONDS - minutes * SECONDSPERMIN;

        tenMills0.setText(valueOf(seconds % 100 / 10));
        tenMills1.setText(valueOf(seconds % 10));
    }

    public static String compressPicture(Context context, String path, int maxWidth, int maxHeight) throws FileNotFoundException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int originHeight = options.outHeight;
        int originWidth = options.outWidth;

        int inSampleSize = 1;
        if (originHeight > maxHeight || originWidth > maxWidth) {

            final int halfHeight = originHeight / 2;
            final int halfWidth = originWidth / 2;
            while ((halfHeight / inSampleSize) >= maxHeight
                    && (halfWidth / inSampleSize) >= maxWidth) {
                inSampleSize *= 2;
            }
        }

        // Calculate inSampleSize
        options.inSampleSize = inSampleSize;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return compress(context, path, options);
    }

    private static String compress(Context context, String originalFilePath, BitmapFactory.Options options) throws FileNotFoundException {
        String outFilePath = getUniqueFileOutputPath(context, ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(originalFilePath, options);
        int maxQuality = calculateMaxQuality(outFilePath, options);
        for (int quality = maxQuality; quality >= 5; quality -= 5) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, new FileOutputStream(outFilePath, false));
            long length = new File(outFilePath).length();
            if (length < 250_000L) {
                break;
            }
        }
        return outFilePath;
    }

    private static int calculateMaxQuality(String path, BitmapFactory.Options options) {
        long compressedSize = new File(path).length() / options.inSampleSize / options.inSampleSize;
        if (compressedSize > 4L * 1024L * 1024L) {
            return 15;
        } else if (compressedSize > 2L * 1024L * 1024L) {
            return 30;
        } else if (compressedSize > 1L * 1024L * 1024L) {
            return 40;
        } else {
            return 50;
        }
    }

    private static String getUniqueFileOutputPath(Context context, String type) {
        String cacheFolder = context.getApplicationContext().getCacheDir().getAbsolutePath();
        return cacheFolder + separator + currentTimeMillis() + type;
    }

    public static void clearToken(SharedPreferences preferences) {
    }

    public static String getWebUrlWithoutQuery(String urlStr) {
        Pattern p = compile("^(http)[s]?.*");
        Matcher matcher = p.matcher(urlStr);
        if (!matcher.find()) {
            return null;
        } else {
            String urlWithoutQuery = null;
            String[] arrSplit = urlStr.trim().split("[?]");
            if (urlStr.length() > 0 && arrSplit.length >= 1 && arrSplit[0] != null) {
                urlWithoutQuery = arrSplit[0];
            }
            return urlWithoutQuery;
        }
    }

    public static void setSpannableClickableString(TextView textView, String origin, String[] clickableStrings, ClickableSpan[] clickableSpen) {
        if (clickableStrings == null || clickableSpen == null || clickableStrings.length != clickableSpen.length)
            return;
        SpannableString spannableString = new SpannableString(origin);

        int start;
        for (int i = 0, size = clickableSpen.length; i < size; i++) {
            start = origin.indexOf(clickableStrings[i]);
            if (start > -1) {
                spannableString.setSpan(clickableSpen[i], start, start + clickableStrings[i].length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void restartApp(Context context) {
//        Intent mStartActivity = new Intent(context, ServerConfigActivity.class);
//        int mPendingIntentId = 123456;
//        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, FLAG_CANCEL_CURRENT);
//        AlarmManager mgr = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        mgr.set(RTC, currentTimeMillis() + 100, mPendingIntent);
//        System.exit(0);
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private static String convertToDateTimeString(long mills, String dateTimeFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(mills);
    }

    private static String convertToDateTimeStringUTC(long mills, String dateTimeFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(mills);
    }


    public static void copy(String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    public static int Dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static String formatFloat(float value) {
        return String.format(Locale.getDefault(), "%.3f", value);
    }

}
