package com.dandan.love.utils;

import com.google.gson.Gson;

/**
 * Created by Tanzhenxing
 * Date: 2018/8/29 上午10:47
 * Description:
 */
public class GsonUtils {
    public static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
