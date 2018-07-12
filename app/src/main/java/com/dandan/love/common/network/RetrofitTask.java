package com.dandan.love.common.network;

import com.dandan.love.common.network.api.GankAPI;

import rx.Observable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:52
 * Description:
 */
public abstract class RetrofitTask<T> implements ITask {
    protected String TAG;
    protected GankAPI gankAPI;
    public RetrofitTask() {
        this.TAG = this.getClass().getSimpleName();
        gankAPI = RetrofitApiFactory.createApi(GankAPI.class);
    }

    @Override
    public void run() {

    }

    public abstract Observable<T> exeForObservable();
}
