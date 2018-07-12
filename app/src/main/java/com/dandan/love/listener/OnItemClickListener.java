package com.dandan.love.listener;

import android.view.View;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public interface OnItemClickListener<T> {
    void onItemClick(View view, T data, int position);
}
