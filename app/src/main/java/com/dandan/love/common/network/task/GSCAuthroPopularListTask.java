package com.dandan.love.common.network.task;

import com.dandan.love.bean.AuthorModel;
import com.dandan.love.common.network.RetrofitTask;
import com.dandan.love.common.network.bean.ErrorResult;
import com.dandan.love.common.network.bean.Wrapper;
import com.dandan.love.config.Config;
import com.dandan.love.utils.GsonUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Tanzhenxing
 * Date: 2018/8/16 上午9:39
 * Description: 获取最受欢迎的作者列表
 */
public class GSCAuthroPopularListTask extends RetrofitTask<List<AuthorModel>>{
    private int pageName;
    private int pageSize;

    public GSCAuthroPopularListTask(int pageName, int pageSize) {
        this.pageName = pageName;
        this.pageSize = pageSize;
    }

    @Override
    public Observable<List<AuthorModel>> exeForObservable() {
        return gushiciApi.getPopularAuthor(pageName, pageSize)
                .subscribeOn(Schedulers.io())
                .map(wrapper -> {
                    if (Wrapper.SUCCESS_CODE == wrapper.code) {
                        JSONArray jsonArray = (JSONArray) wrapper.result;
                        if (null != jsonArray) {
                            List<AuthorModel> list = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    AuthorModel authorModel = GsonUtils.fromJson(jsonArray.getJSONObject(i).toString(), AuthorModel.class);
                                    authorModel.setIcon(Config.GUSHICI_IMAGE_URL + authorModel.getIcon());
                                    list.add(authorModel);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            return list;
                        }
                    }
                    throw new ErrorResult(wrapper.code, wrapper.msg);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
