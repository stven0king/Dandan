package com.dandan.love.common.network.bean;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/24 下午10:27
 * Description: 易源接口model
 */
public class YYWrapper {
    //0成功
    //-1，系统调用错误
    //-2，可调用次数或金额为0
    //-3，读取超时
    //-4，服务端返回数据解析错误
    //-5，后端服务器DNS解析错误
    //-6，服务不存在或未上线
    //-1000，系统维护
    //-1002，showapi_appid字段必传
    //-1003，showapi_sign字段必传
    //-1004，签名sign验证有误
    //-1005，showapi_timestamp无效
    //-1006，app无权限调用接口
    //-1007，没有订购套餐
    //-1008，服务商关闭对您的调用权限
    //-1009，调用频率受限
    //-1010，找不到您的应用
    //-1011，子授权app_child_id无效
    //-1012，子授权已过期或失效
    //-1013，子授权ip受限
    public int showapi_res_code;
    public String showapi_res_error;

    public Object showapi_res_body;

    public static class JsonAdapter implements JsonDeserializer<YYWrapper> {

        @Override
        public YYWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                String root = json.getAsJsonObject().toString();
                YYWrapper wrapper = new YYWrapper();
                JSONObject jsonObject = new JSONObject(root);
                wrapper.showapi_res_code = jsonObject.getInt("showapi_res_code") ;
                wrapper.showapi_res_error = jsonObject.getString("showapi_res_error") ;
                wrapper.showapi_res_body = jsonObject.get("showapi_res_body") ;
                return wrapper;
            } catch (JSONException e) {
                throw  new JsonParseException(e);
            }
        }
    }
}
