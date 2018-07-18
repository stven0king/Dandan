package com.dandan.love.common.image;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.dandan.love.common.download.CallBack;
import com.dandan.love.common.download.DExecutors;
import com.dandan.love.config.GlideApp;
import com.dandan.love.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DownImageImpl {
    private static String TAG = "DownImageImpl";
    /**
     * Glide 获得图片缓存路径
     */
    public static File getImagePath(Context context, String imgUrl) {
        File cacheFile = null;
        FutureTarget<File> future = GlideApp
                .with(context)
                .downloadOnly()
                .load(imgUrl)
                .submit();
        try {
            cacheFile = future.get();
            Log.d(TAG, cacheFile.getAbsolutePath());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return cacheFile;
    }

    public static void saveImageToDisk(final Context context, final String imgUrl,
                                       final String imageDir, final String imageName, final CallBack callBack) {
        DExecutors.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    File sourceFile = getImagePath(context, imgUrl);
                    File imageDirFile = new File(imageDir);
                    if (!imageDirFile.exists()) {
                        imageDirFile.mkdirs();
                    }
                    File newFile = new File(imageDirFile, imageName);
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    FileUtils.copyFile(sourceFile.getPath(), newFile.getAbsolutePath());
                    callBack.call(CallBack.SCUESS);
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.call(CallBack.FAILURE);
                }
            }
        });
    }

    public static void saveImageToDisk(final Context context, final String imgUrl, String imageName, CallBack callBack) {
        saveImageToDisk(context, imgUrl, FileUtils.getImageDiskCacheFile().getAbsolutePath(), imageName, callBack);
    }
}
