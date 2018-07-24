package com.dandan.love.common.network.api;


import com.dandan.love.common.network.bean.YYWrapper;
import com.dandan.love.common.network.converter.Host;
import com.dandan.love.config.RetrofitInterfaceConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/24 下午8:58
 * Description: 易源接口
 */

@Host(
        publish = RetrofitInterfaceConfig.YIYUAN_API,
        test = RetrofitInterfaceConfig.YIYUAN_API,
        isDebug = false
)
public interface YiYuanApi {

    String APP_ID = "70724";
    String APP_SIGN = "d1aef7feb40649e18115b040f223646a";


    @GET(RetrofitInterfaceConfig.YIYUAN_API_HUABAN)
    Observable<YYWrapper> getHuaBanData(@Query("showapi_appid") String appid,
                                               @Query("showapi_sign") String sign,
                                               @Query("showapi_timestamp") String timestamp,
                                               @Query("type") String type,
                                               @Query("num") String num,
                                               @Query("page") String page);
}
