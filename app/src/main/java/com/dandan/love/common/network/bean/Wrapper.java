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
 * Date: 2018/8/16 上午9:26
 * Description:
 */
public class Wrapper {
    public static final int SUCCESS_CODE = 1;

    public int code;
    public String msg;

    public Object result;

    public static class JsonAdapter implements JsonDeserializer<Wrapper> {

        @Override
        public Wrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                String root = json.getAsJsonObject().toString();
                Wrapper wrapper = new Wrapper();
                JSONObject jsonObject = new JSONObject(root);
                wrapper.code = jsonObject.getInt("code") ;
                wrapper.msg = jsonObject.getString("msg") ;
                wrapper.result = jsonObject.get("result") ;
                return wrapper;
            } catch (JSONException e) {
                throw  new JsonParseException(e);
            }
        }
    }
}
