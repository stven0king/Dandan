package com.dandan.love.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dandan.love.base.BaseRecycleAdapter;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description: recycle的base entity
 */
public class RecycleItemEntity<T> implements MultiItemEntity {

    private T data;

    public RecycleItemEntity(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }


    int currentType = BaseRecycleAdapter.TYPE_DATA;

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }

    @Override
    public int getItemType() {
        return currentType;
    }
}
