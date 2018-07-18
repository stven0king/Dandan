package com.dandan.love.common.logger;

import android.content.Context;
import android.util.SparseArray;

import com.dandan.love.common.logger.constant.LogConstants;
import com.dandan.love.common.logger.core.LogBuilder;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.logger.style.XLogStyle;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午8:39
 * Description:
 */
public class DandanLog {
    private static DandanLog instance = null;
    private SparseArray collection = new SparseArray();
    private Context mContext ;

    private DandanLog() {
    }
    private static DandanLog getInstance () {
        if (instance == null) {
            instance = new DandanLog();
        }
        return instance;
    }


    public static void init(Context context , boolean isDubug){

        Logger.uprootAll();
        Logger.initialize(LogBuilder.create().logPrintStyle(new XLogStyle()).showMethodLink(false)
                .showThreadInfo(false).tagPrefix(LogConstants.LOG_TAG_PREFIX).methodOffset(0).build());
        Logger.setDebug(isDubug);
    }
}
