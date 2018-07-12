package com.dandan.love.common.network.converter;

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
public class ByteArrayConverterFactory extends Converter.Factory {
    private static ByteArrayConverterFactory INSTANCE = new ByteArrayConverterFactory() ;

    private ByteArrayResponseConverter converter  = new ByteArrayResponseConverter() ;

    private ByteArrayConverterFactory(){}

    public static ByteArrayConverterFactory create(){return  INSTANCE;}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if(type != null && type.toString().equals("byte[]")) {
            return converter;
        } else {
            return null;
        }
    }

    private class ByteArrayResponseConverter implements Converter<ResponseBody, byte[]>{
        @Override
        public byte[] convert(ResponseBody value) throws IOException {
            return value.bytes();
        }
    }
}