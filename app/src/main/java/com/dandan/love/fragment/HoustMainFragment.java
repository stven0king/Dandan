package com.dandan.love.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dandan.love.R;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.common.logger.core.Logger;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 下午2:29
 * Description:
 */
public class HoustMainFragment extends BaseLazyFragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_houst_main_layout, container, false);
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(TAG, "onDestroyView");
    }
}
