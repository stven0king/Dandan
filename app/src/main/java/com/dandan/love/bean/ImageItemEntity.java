package com.dandan.love.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dandan.love.base.BaseRecycleAdapter;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description: recycle图片model
 */
public class ImageItemEntity<T> implements MultiItemEntity {

    private T data;

    public ImageItemEntity(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }


    @Override
    public int getItemType() {
        return BaseRecycleAdapter.TYPE_DATA;
    }
}
