package com.dandan.love.common.network.task;

import com.dandan.love.bean.YYHuaBanModel;
import com.dandan.love.common.network.RetrofitTask;
import com.dandan.love.common.network.api.YiYuanApi;
import com.dandan.love.common.network.bean.ErrorResult;
import com.dandan.love.common.network.bean.YYWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/24 下午9:07
 * Description: 易源----花瓣
 */
public class YyHuabanDataGetTask extends RetrofitTask<ArrayList<YYHuaBanModel>>{
    public static Map<String, String> typeMaps = new HashMap<>();
    static {
        typeMaps.put("大胸妹", "34");
        typeMaps.put("小清新", "35");
        typeMaps.put("文艺范", "36");
        typeMaps.put("性感妹", "37");
        typeMaps.put("大长腿", "38");
        typeMaps.put("黑丝袜", "39");
        typeMaps.put("小翘臀", "40");
    }

    private static int pageNum = 12;
    private int pageName = 0;
    private String type = "36";

    public YyHuabanDataGetTask(int pageName, String type) {
        this.pageName = pageName;
        this.type = type;
    }

    @Override
    public Observable<ArrayList<YYHuaBanModel>> exeForObservable() {
        //yyyyMMddHHmmss
        SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String timestamp = formatDay.format(new Date());
        return yiYuanApi.getHuaBanData(YiYuanApi.APP_ID, YiYuanApi.APP_SIGN,
                timestamp, type, String.valueOf(pageNum), String.valueOf(pageName))
                .subscribeOn(Schedulers.io())
                .map(new Func1<YYWrapper, ArrayList<YYHuaBanModel>>() {
                    @Override
                    public ArrayList<YYHuaBanModel> call(YYWrapper yyWrapper) {
                        ArrayList<YYHuaBanModel> result = new ArrayList<>();
                        if (yyWrapper.showapi_res_code == 0) {
                            JSONObject jsonObject = (JSONObject) yyWrapper.showapi_res_body;
                            for (int i = 0; i < pageNum; i++) {
                                JSONObject json = jsonObject.optJSONObject(String.valueOf(i));
                                if (null != json) {
                                    YYHuaBanModel model = YYHuaBanModel.parse(json);
                                    if (model != null) {
                                        result.add(model);
                                    }
                                }
                            }
                            return result;
                        }
                        throw new ErrorResult(yyWrapper.showapi_res_code, yyWrapper.showapi_res_error);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
