package com.dandan.love.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dandan.love.App;
import com.dandan.love.common.logger.core.Logger;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:11
 * Description:
 */
public class AndroidUtils {
    private static String TAG = "AndroidUtils";
    public static String getAppCacheDir() {
        String cachePath = null;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    || !Environment.isExternalStorageRemovable()) {
                cachePath = App.getApp().getExternalCacheDir().getPath();
            } else {
                cachePath = App.getApp().getCacheDir().getPath();
            }
            Logger.d(TAG, cachePath + "\n" + App.getApp().getExternalCacheDir().getPath() + "\n" + App.getApp().getCacheDir()
                    .getPath());
        }catch (Exception e){

        }
        return cachePath;
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = activity.getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }
    }

    public static void shareText(Context context, String msg) {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
        context.startActivity(Intent.createChooser(textIntent, "分享"));
    }

    public static void shareImage(Context context, Uri uri) {
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/jpeg");
        imageIntent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(imageIntent, "分享"));
    }

    public static String getResourcesUri(Context context, @DrawableRes int id) {
        Resources resources = context.getApplicationContext().getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }
}
