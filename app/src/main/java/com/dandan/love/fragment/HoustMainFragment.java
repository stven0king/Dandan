package com.dandan.love.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.R;
import com.dandan.love.activity.MainActivity;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.logger.core.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 下午2:29
 * Description:
 */
@SuppressLint("ValidFragment")
public class HoustMainFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener{
    private MainActivity activity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<GankIOClassifyModel> list = new ArrayList<>();
    private BaseRecycleAdapter<RecycleItemEntity<GankIOClassifyModel>> mAdapter;
    public HoustMainFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_houst_main_layout, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        //设置刷新的颜色
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.background_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark);
        //拖动多长的时候开始刷新
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置大小
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mAdapter = new BaseRecycleAdapter<RecycleItemEntity<GankIOClassifyModel>>(list) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_houst_main);
            }

            @Override
            protected void convert(BaseViewHolder helper, RecycleItemEntity<GankIOClassifyModel> item) {

            }
        };
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

    @Override
    public void onRefresh() {

    }

    private RecyclerView.OnScrollListener  scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                Logger.d(TAG, "loading executed");

                boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                if (!isRefreshing) {
                    swipeRefreshLayout.setRefreshing(true);

                }

            }
        }
    };

    private void completeRefreshData() {
        if (null != swipeRefreshLayout) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
