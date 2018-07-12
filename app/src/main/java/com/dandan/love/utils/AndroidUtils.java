package com.dandan.love.utils;

import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;

import com.dandan.love.App;

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
            Log.d(TAG, cachePath + "\n" + App.getApp().getExternalCacheDir().getPath() + "\n" + App.getApp().getCacheDir()
                    .getPath());
        }catch (Exception e){

        }
        return cachePath;
    }
}
