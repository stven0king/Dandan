package com.dandan.love.common.network.task;

import com.dandan.love.bean.GushiwenModel;
import com.dandan.love.common.network.RetrofitTask;
import com.dandan.love.common.network.bean.ErrorResult;
import com.dandan.love.common.network.bean.Wrapper;
import com.dandan.love.utils.GsonUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/3 下午3:00
 * Description: 获取诗人作品列表
 */
public class GSCGushiwenListByAuthor extends RetrofitTask<List<GushiwenModel>> {
    private int pageName;
    private int pageSize;
    private String aname;

    public GSCGushiwenListByAuthor(int pageName, int pageSize, String aname) {
        this.pageName = pageName;
        this.pageSize = pageSize;
        this.aname = aname;
    }

    @Override
    public Observable<List<GushiwenModel>> exeForObservable() {
        return gushiciApi.getGushiwenListByAuthor(aname, pageName, pageSize)
                .subscribeOn(Schedulers.io())
                .map(wrapper -> {
                    if (Wrapper.SUCCESS_CODE == wrapper.code) {
                        JSONArray jsonArray = (JSONArray) wrapper.result;
                        if (null != jsonArray) {
                            List<GushiwenModel> list = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    GushiwenModel model = GsonUtils.fromJson(jsonArray.getJSONObject(i).toString(), GushiwenModel.class);
                                    list.add(model);
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
