package com.dandan.love.listener;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public interface OnHeaderClickListener<T> {
    void onHeaderClick(int id, int position, T data);

    void onHeaderLongClick(int id, int position, T data);

    void onHeaderDoubleClick(int id, int position, T data);
}
