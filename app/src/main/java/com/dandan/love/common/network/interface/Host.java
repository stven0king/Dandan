package com.dandan.love.common.network.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:18
 * Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Host {

    String publish() ;

    String test() default "";

    boolean isDebug() default false ;
}
