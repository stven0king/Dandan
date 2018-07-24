package com.dandan.love.common.network;

import android.text.TextUtils;
import android.util.Log;

import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.network.bean.YYWrapper;
import com.dandan.love.common.network.converter.ByteArrayConverterFactory;
import com.dandan.love.common.network.converter.Host;
import com.dandan.love.common.network.converter.JSONObjectResponseConverterFactory;
import com.dandan.love.common.network.converter.StringResponseConverterFactory;
import com.dandan.love.config.Config;
import com.dandan.love.config.FileConfig;
import com.dandan.love.utils.AndroidUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public class RetrofitApiFactory {



    private static final int TIME_OUT = 30;

    private static final ConcurrentHashMap<Class,Object> cacheMap
            = new ConcurrentHashMap<Class,Object>() ;

    private static OkHttpClient okHttpClient = null ;
    private static File cacheFile = new File(AndroidUtils.getAppCacheDir(), FileConfig.NETWORK_CACHE);
    private static Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(YYWrapper.class, new YYWrapper.JsonAdapter())
            .create();
    static{
        /*
         * 初始化OkHttpClient
         */
        initOkHttpClient() ;
    }

    public static <T> T createApi(Class<T> clz){

        Object cacheObj = cacheMap.get(clz) ;
        if(cacheObj != null){
            return (T) cacheObj;
        }

        String baseUrl = "" ;
        Host host = clz.getAnnotation(Host.class) ;
        if(host == null){
            throw new IllegalArgumentException("请在" +clz.getSimpleName()+"接口上添加host配置") ;
        }
        if(Config.DEBUG_MODE && (host.isDebug())){
            baseUrl = host.test() ;
        }

        if(TextUtils.isEmpty(baseUrl)){
            baseUrl = host.publish() ;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ByteArrayConverterFactory.create())
                .addConverterFactory(JSONObjectResponseConverterFactory.create())
                .addConverterFactory(StringResponseConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        T t = retrofit.create(clz) ;
        cacheMap.put(clz,t) ;
        return t ;
    }

    public static void clearCacheMap(){
        cacheMap.clear();
    }

    public static void initOkHttpClient(){
        Logger.d("RetrofitApiFactory","--initOkHttpClient--") ;
        okHttpClient =
                new OkHttpClient.Builder()
                        .cache(cache)
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .addInterceptor(new JobInterceptor())  //only 有网情况下，一分钟内每次请求都会重新请求，不会走缓存
                        .addNetworkInterceptor(new JobNetworkInterceptor())    //only 如果超过1分钟，离线请求不成功
                        .build();
        clearCacheMap() ;
    }
}
