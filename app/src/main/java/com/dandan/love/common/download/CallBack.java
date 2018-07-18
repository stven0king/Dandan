package com.dandan.love.common.download;

public interface CallBack {
    String SCUESS = "SCUESS";
    String FAILURE = "FAILURE";
    boolean call(String msg);
}
