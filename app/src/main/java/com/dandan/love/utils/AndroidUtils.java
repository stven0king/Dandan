package com.dandan.love.utils;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.os.Environment;
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

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = activity.getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }
    }
}
