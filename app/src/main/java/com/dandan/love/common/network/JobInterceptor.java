package com.dandan.love.common.network;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:32
 * Description:
 */
public class JobInterceptor implements Interceptor {
    private String TAG = "JobInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*-------------请求的时候添加header---------------*/
        Request request = chain.request();
        /*-------------请求的时候输出log---------------*/
        String requestUrl = request.url().url().toString();
        Log.d(TAG, "[intercept] request :" + requestUrl);
        long startTime = System.currentTimeMillis();
        /*------------请求如果失败初始化 okhttpclient---*/
        Response response = null;
        Map<String, String> reportMap = new HashMap<>();
        response = chain.proceed(request);
        if (!response.isSuccessful()) {
            RetrofitApiFactory.initOkHttpClient();
        } else {
            int maxAge = request.cacheControl().maxAgeSeconds();
            if (request.cacheControl().isPublic() && maxAge > 1) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        }
        return response;
    }
}
