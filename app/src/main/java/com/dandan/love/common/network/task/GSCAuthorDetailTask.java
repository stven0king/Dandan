package com.dandan.love.common.network.task;

import com.dandan.love.bean.AuthorZiliaoModel;
import com.dandan.love.common.network.RetrofitTask;
import com.dandan.love.common.network.bean.ErrorResult;
import com.dandan.love.common.network.bean.Wrapper;
import com.dandan.love.utils.GsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tanzhenxing
 * Date: 2018/8/29 上午11:16
 * Description:
 */
public class GSCAuthorDetailTask extends RetrofitTask<List<AuthorZiliaoModel>>{
    private String name;

    public GSCAuthorDetailTask(String name) {
        this.name = name;
    }

    @Override
    public Observable<List<AuthorZiliaoModel>> exeForObservable() {
        return gushiciApi.getAuthorDetail(name)
                .subscribeOn(Schedulers.io())
                .map(wrapper -> {
                    if (Wrapper.SUCCESS_CODE == wrapper.code) {
                        JSONObject jsonObject = (JSONObject) wrapper.result;
                        JSONArray ziliaoJson = jsonObject.optJSONArray("ziliao");
                        if (null != ziliaoJson) {
                            List<AuthorZiliaoModel> authorZiliaoModelList = new ArrayList<>();
                            for (int i = 0; i < ziliaoJson.length(); i++) {
                                authorZiliaoModelList.add(GsonUtils.fromJson(ziliaoJson.optJSONObject(i).toString(), AuthorZiliaoModel.class));
                            }
                            return authorZiliaoModelList;
                        }
                    }
                    throw new ErrorResult(wrapper.code, wrapper.msg);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
