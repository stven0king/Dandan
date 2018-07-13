package com.dandan.love.common.network.task;

import android.util.Log;

import com.dandan.love.bean.BaiDuImageModel;
import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.common.network.RequestParams;
import com.dandan.love.common.network.RetrofitTask;
import com.dandan.love.config.RetrofitInterfaceConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/12 下午1:18
 * Description:
 */
public class BaiduImageGetListTask extends RetrofitTask<ArrayList<BaiDuImageModel>>{
    String url = "http://image.baidu.com/i?tn=resultjsonavstar&ie=utf-8&word=刘德华&pn=0&rn=60";
    String url1 = "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=明星&tag2=全部&ftags=女明星&ie=utf8";
    String url2 = "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=美女&tag2=全部&ftags=小清新&ie=utf8";
    String url3 = "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&queryWord=%E4%B8%9D%E8%A2%9C%E7%BE%8E%E5%A5%B3&ie=utf-8&oe=utf-8&word=%E4%B8%9D%E8%A2%9C%E7%BE%8E%E5%A5%B3&pn=0&rn=30";
    private int pn;
    private int rn = 30;
    private String words;
    private RequestParams params = new RequestParams();
    public BaiduImageGetListTask(int pn, String words) {
        this.pn = pn;
        this.words = words;
        params.put(getBaiImageRequestParams());
        params.put("pn", pn);
        params.put("rn", rn);
        params.put("queryWord", words);
        params.put("word", words);
    }

    @Override
    public Observable<ArrayList<BaiDuImageModel>> exeForObservable() {
        return freedomApi.getJsonObject(RetrofitInterfaceConfig.BAIDU_IMAGE_SEARCH_URL, params.params())
                .subscribeOn(Schedulers.io())
                .map(parase)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Func1<JSONObject, ArrayList<BaiDuImageModel>> parase = new Func1<JSONObject, ArrayList<BaiDuImageModel>>() {
        @Override
        public ArrayList<BaiDuImageModel> call(JSONObject jsonObject) {
            Log.d(TAG, jsonObject.toString());
            ArrayList<BaiDuImageModel> result = null;
            try {
                if (null != jsonObject && jsonObject.has("data")) {
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    if (null != jsonArray && jsonArray.length() > 0) {
                        result = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            BaiDuImageModel model = BaiDuImageModel.parse(jsonArray.getJSONObject(i));
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
    };

    public static Map<String, String> getBaiImageRequestParams() {
        RequestParams params = new RequestParams();
        params.put("tn", "resultjson_com");
        params.put("ipn", "rj");
        params.put("ie", "utf-8");
        params.put("oe", "utf-8");
        return params.params();
    }
}
