package com.dandan.love.common.network.api;

import com.dandan.love.common.network.converter.Host;
import com.dandan.love.config.RetrofitInterfaceConfig;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:42
 * Description:
 */

@Host(
        publish = RetrofitInterfaceConfig.GANK_IO,
        test = RetrofitInterfaceConfig.GANK_IO,
        isDebug = false
)
public interface GankAPI {
    @GET(RetrofitInterfaceConfig.GANK_CLASSIFY_URL)
    Observable<JSONObject> getclassifyListData(@Path("type") String type,
                                               @Path("pagesize") int pagesize,
                                               @Path("pagenum") int pagenum);
}
