package com.dandan.love.base;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dandan.love.listener.PinnedHeaderNotifyer;
import com.dandan.love.utils.FullSpanUtil;

import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public abstract class BaseRecycleAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {
    public static final int TYPE_DATA = 0;
    public static final int TYPE_HEADER = 1;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseRecycleAdapter(List data) {
        super(data);
        addItemTypes();
    }

    protected abstract void addItemTypes();


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }
}
