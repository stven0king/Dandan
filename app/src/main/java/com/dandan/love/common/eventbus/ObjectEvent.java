package com.dandan.love.common.eventbus;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 下午3:29
 * Description:
 */
public class ObjectEvent<T> implements Event {
    private T object;
    private String action;

    public ObjectEvent(String action, T object) {
        this.action = action;
        this.object = object;
    }

    @Override
    public String action() {
        return action;
    }

    public T getObject() {
        return object;
    }
}