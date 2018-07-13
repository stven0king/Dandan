package com.dandan.love.common.network.api;


import com.dandan.love.common.network.converter.Host;
import com.dandan.love.config.RetrofitInterfaceConfig;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

@Host(
        publish = RetrofitInterfaceConfig.GANK_IO
)
public interface FreedomApi {
    @GET
    Call<ResponseBody> getLoadData(@Url String url);

    @GET
    Observable<JSONObject> getJsonObject(@Url String url);

    @GET
    Observable<JSONObject> getJsonObject(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Call<ResponseBody> getLoadData(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Call<ResponseBody> postLoadData(@Url String url);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> jobProxy(@Url String url, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Call<JSONObject> postTask(@Url String url, @FieldMap Map<String, String> map);

    @Headers("Cache-Control: public, max-age=86400")
    @GET
    Call<ResponseBody> getCacheData(@Url String url, @QueryMap Map<String, String> map);

    /**
     * 长时间缓存的 6个月
     */
    @Headers("Cache-Control: public, max-age=15552000")
    @GET
    Call<ResponseBody> getMaxCacheData(@Url String url, @QueryMap Map<String, String> map);
}
