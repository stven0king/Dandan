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
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.R;
import com.dandan.love.activity.MainActivity;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.activity.BaseWebViewActivity;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.network.SimpleSubscriber;
import com.dandan.love.common.network.task.GankDataGetListTask;
import com.dandan.love.config.GlideApp;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 下午2:29
 * Description:
 */
@SuppressLint("ValidFragment")
public class PharosMainFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener{
    private MainActivity activity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<RecycleItemEntity<GankIOClassifyModel>> listData = new ArrayList<>();
    private BaseRecycleAdapter<RecycleItemEntity<GankIOClassifyModel>> mAdapter;

    private int pageName = 1;

    public PharosMainFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharos_main_layout, container, false);
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
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new BaseRecycleAdapter<RecycleItemEntity<GankIOClassifyModel>>(listData) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_pharos_main);
            }

            @Override
            protected void convert(BaseViewHolder holder, RecycleItemEntity<GankIOClassifyModel> item) {
                switch (holder.getItemViewType()) {
                    case BaseRecycleAdapter.TYPE_DATA:
                        GankIOClassifyModel model = item.getData();
                        holder.setText(R.id.time_tv, model.getCreateTime().substring(0, model.getCreateTime().indexOf("T")));
                        holder.setText(R.id.desc_tv, model.getDesc());
                        holder.setText(R.id.author_tv, "作者：" + model.getWho());
                        ImageView imageView = holder.getView(R.id.image_iv);
                        if (null != model.getImages() && model.getImages().size() > 0) {
                            holder.setVisible(R.id.image_layout, true);
                            holder.setText(R.id.image_tv, model.getType());
                            GlideApp.with(getActivity()).load(model.getImages().get(0)).into(imageView);
                        } else {
                            holder.setVisible(R.id.image_layout, false);
                        }
                        break;
                }
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener(){


            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final RecycleItemEntity<GankIOClassifyModel> entity = mAdapter.getData().get(position);
                BaseWebViewActivity.startActivituy(getActivity(), entity.getData().getUrl());
            }
        });
        initData();
        return view;
    }

    @Override
    protected void lazyLoad() {
        Logger.d(TAG, "lazyLoad: ");

    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh: ");
        pageName = 1;
        initGankioEvent();

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
                    initGankioEvent();
                }

            }
        }
    };

    private void initData() {
        initGankioEvent();
    }

    private void initGankioEvent() {
        Subscription s = submitForObservable(new GankDataGetListTask(GankDataGetListTask.CLASSIFY_TYPE[1], pageName))
                .subscribe(new SimpleSubscriber<ArrayList<GankIOClassifyModel>>() {
                    @Override
                    public void onNext(ArrayList<GankIOClassifyModel> list) {
                        if (pageName == 1) {
                            listData.clear();
                        }
                        if (null != list && list.size() > 0) {
                            for (GankIOClassifyModel model:list) {
                                listData.add(new RecycleItemEntity<>(model));
                            }
                            pageName++;
                            mAdapter.notifyDataSetChanged();
                            completeRefreshData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
        addSubscription(s);
    }

    private void completeRefreshData() {
        if (null != swipeRefreshLayout) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
