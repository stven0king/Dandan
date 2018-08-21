package com.dandan.love.common.network.api;

import com.dandan.love.common.network.bean.Wrapper;
import com.dandan.love.common.network.converter.Host;
import com.dandan.love.config.RetrofitInterfaceConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tanzhenxing
 * Date: 2018/8/16 上午9:22
 * Description:
 */

@Host(
        publish = RetrofitInterfaceConfig.GUSHICI_URL,
        test = RetrofitInterfaceConfig.GUSHICI_URL,
        isDebug = false
)
public interface GushiciApi {

    /**
     * 获取最受欢迎的作者
     * @param p
     * @param s
     * @return
     */
    @GET(RetrofitInterfaceConfig.GUSHICI_AUTHOR_POPULAR)
    Observable<Wrapper> getPopularAuthor(@Query("p") int p,
                                         @Query("s") int s);

    /**
     * 或者作者详情
     * @param name
     * @return
     */
    @GET(RetrofitInterfaceConfig.GUSHICI_AUTHOR_DETAIL)
    Observable<Wrapper> getAuthorDetail(@Query("name") String name);
}
