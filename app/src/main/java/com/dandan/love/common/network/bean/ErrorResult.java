package com.dandan.love.common.network.bean;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/24 下午10:56
 * Description:
 */
public class ErrorResult extends RuntimeException {
    private static final long serialVersionUID = 5534026586981649648L;
    private int code ;
    private String msg ;

    public ErrorResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorResult() {
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
