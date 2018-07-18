package com.dandan.love.common.network;

import android.util.Log;

import com.dandan.love.common.logger.core.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:36
 * Description:
 */
public class JobNetworkInterceptor implements Interceptor {
    private String TAG = "JobNetworkInterceptor" ;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.isSuccessful()) {
            int maxAge = request.cacheControl().maxAgeSeconds();
            if (request.cacheControl().isPublic() && maxAge > 1) {
                Log.d(TAG, "response need cache~!");
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .removeHeader("Expires")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        }
        return response;
    }
}