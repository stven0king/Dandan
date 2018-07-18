package com.dandan.love.common.network.task;

import android.util.Log;

import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.network.RetrofitTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:51
 * Description:
 */
public class GankDataGetListTask extends RetrofitTask<ArrayList<GankIOClassifyModel>>{
    public static String[] CLASSIFY_TYPE = {"福利", "Android", "iOS", "休息视频", "拓展资源", "前端", "all"};

    private int pageSize = 10;
    private int pageNum;
    private String type = CLASSIFY_TYPE[6];

    public GankDataGetListTask(String type, int pageNum) {
        this.pageNum = pageNum;
        this.type = type;
    }

    @Override
    public Observable<ArrayList<GankIOClassifyModel>> exeForObservable() {
        return gankAPI.getclassifyListData(type, pageSize, pageNum)
                .subscribeOn(Schedulers.io())
                .map(new Func1<JSONObject, ArrayList<GankIOClassifyModel>>() {
                    @Override
                    public ArrayList<GankIOClassifyModel> call(JSONObject jsonObject) {
                        Logger.d(TAG, jsonObject.toString());
                        ArrayList<GankIOClassifyModel> result = null;
                        try {
                            if (null != jsonObject && jsonObject.has("results")) {
                                JSONArray jsonArray = jsonObject.optJSONArray("results");
                                if (null != jsonArray && jsonArray.length() > 0) {
                                    result = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        GankIOClassifyModel model = GankIOClassifyModel.parse(jsonArray.getJSONObject(i));
                                        if (null != model) {
                                            result.add(model);
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
