package com.dandan.love.listener;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public interface PinnedHeaderNotifyer<T> {
    boolean isPinnedHeaderType(int viewType);

    T getPinnedHeaderInfo(int position);
}
