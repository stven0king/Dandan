package com.dandan.love;

import android.app.Application;

import com.dandan.love.common.logger.DandanLog;
import com.dandan.love.config.Config;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public class App extends Application{
    public static Application application = null;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        DandanLog.init(this, Config.DEBUG_MODE);
    }

    public static Application getApp() {
        return application;
    }
}
