package com.dandan.love.base;

import android.os.Bundle;

import com.dandan.love.common.logger.core.Logger;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 上午9:58
 * Description:
 */
public abstract class BaseLazyFragment extends BaseFragment{
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean mHasLoadedOnce;

    private boolean isLoad = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLoad = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.d(TAG, "setUserVisibleHint:  " + isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible() {
        lazyLoad();
        if (!isLoad) {
            lazyLoadOnce();
            isLoad = true;
        }
    }
    protected void onInvisible() {

    }

    protected abstract void lazyLoad();

    /**
     * 只加载一次
     */
    protected void lazyLoadOnce() {

    }
}
