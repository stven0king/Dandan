package com.dandan.love.common.network;

import com.dandan.love.common.logger.core.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import rx.Subscriber;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午8:39
 * Description:
 */
public class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {}
    @Override
    public void onError(Throwable e) {
        Logger.w("SimpleSubscriber","[onError]" + getStackTrace(e)) ;
    }
    @Override
    public void onNext(T t) { }

    /**
     * 完整的堆栈信息
     *
     * @param e Exception
     * @return Full StackTrace
     */
    private static String getStackTrace(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw == null ? "" : sw.toString();
    }
}
