package com.dandan.love;

import android.app.Application;

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
    }

    public static Application getApp() {
        return application;
    }
}
