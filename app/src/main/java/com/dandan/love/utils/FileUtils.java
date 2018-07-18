package com.dandan.love.utils;

import android.os.Environment;

import com.dandan.love.config.FileConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:11
 * Description:
 */
public class FileUtils {
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static File getImageDiskCacheFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory(FileConfig.DIR_ROOT),
                FileConfig.IMAGE_DOWNLOAD_DISK_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getCrashLogFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory(FileConfig.DIR_ROOT),
                FileConfig.CRASH_LOG_FILE_NAME);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
