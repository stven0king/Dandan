package com.dandan.love.common.network.converter;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public class JSONObjectResponseConverterFactory extends Converter.Factory  {

    private String TAG = "JSONObjectResponseConverter";

    private static JSONObjectResponseConverterFactory INSTANCE = new JSONObjectResponseConverterFactory() ;

    private JSONObjectResponseConverter converter  = new JSONObjectResponseConverter() ;

    private JSONObjectResponseConverterFactory(){}

    public static JSONObjectResponseConverterFactory create(){return  INSTANCE;}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if(type != null && type == JSONObject.class) {
            return converter;
        } else {
            return null;
        }
    }

    private class JSONObjectResponseConverter implements Converter<ResponseBody, JSONObject>{
        @Override
        public JSONObject convert(ResponseBody value) throws IOException {
            try {
                String sValue = value.string();
                Log.d(TAG,"response : " + sValue) ;
                return new JSONObject(sValue);
            } catch (JSONException e) {
                throw new IllegalArgumentException(e) ;
            }
        }
    }
}