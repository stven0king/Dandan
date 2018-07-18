package com.dandan.love.common.network.converter;

import android.util.Log;

import com.dandan.love.common.logger.core.Logger;

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
public class StringResponseConverterFactory extends Converter.Factory  {
    private String TAG = "StringResponseConverterFactory";
    private static StringResponseConverterFactory INSTANCE = new StringResponseConverterFactory() ;

    private StringResponseConverter converter  = new StringResponseConverter() ;

    private StringResponseConverterFactory(){}

    public static StringResponseConverterFactory create(){return  INSTANCE;}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if(type != null && type == String.class) {
            return converter;
        } else {
            return null;
        }
    }

    private class StringResponseConverter implements Converter<ResponseBody, String>{
        @Override
        public String convert(ResponseBody value) throws IOException {
            String sValue = value.string();
            Log.d(TAG,"response : " + sValue) ;
            return sValue;
        }
    }
}