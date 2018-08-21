package com.dandan.love.common.network;

import com.dandan.love.common.network.api.FreedomApi;
import com.dandan.love.common.network.api.GankAPI;
import com.dandan.love.common.network.api.GushiciApi;
import com.dandan.love.common.network.api.YiYuanApi;

import rx.Observable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:52
 * Description:
 */
public abstract class RetrofitTask<T> implements ITask {
    protected String TAG;
    protected GankAPI gankAPI;
    protected FreedomApi freedomApi;
    protected YiYuanApi yiYuanApi;
    protected GushiciApi gushiciApi;
    public RetrofitTask() {
        this.TAG = this.getClass().getSimpleName();
        gankAPI = RetrofitApiFactory.createApi(GankAPI.class);
        freedomApi = RetrofitApiFactory.createApi(FreedomApi.class);
        yiYuanApi = RetrofitApiFactory.createApi(YiYuanApi.class);
        gushiciApi = RetrofitApiFactory.createApi(GushiciApi.class);
    }

    @Override
    public void run() {

    }

    public abstract Observable<T> exeForObservable();
}
