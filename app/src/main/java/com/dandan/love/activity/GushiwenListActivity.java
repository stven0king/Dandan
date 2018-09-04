package com.dandan.love.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dandan.love.R;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.bean.GushiwenModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.view.adapter.GushiwenRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/3 下午7:50
 * Description:
 */
public abstract class GushiwenListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static final String TITLE = "TITLE";
    public String title = "";
    private GushiwenRecyclerAdapter mAdapter;
    protected List<RecycleItemEntity<GushiwenModel>> listData = new ArrayList<>();
    protected int pageName = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gushiwen_list_layout);
        getIntentTitle();
        initView();
        initData();
    }

    private void getIntentTitle() {
        if (null != getIntent()) {
            title = getIntent().getStringExtra(TITLE);
        }
    }

    private void initView() {
        initToolBar();
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
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
        mAdapter = new GushiwenRecyclerAdapter(listData);
        recyclerView = findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(scrollListener);
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back_arrow);
        toolbar.setNavigationOnClickListener(v -> finish());
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
                    initData();
                }

            }
        }
    };

    public abstract void initData();

    @Override
    public void onRefresh() {
        pageName = 1;
        initData();
    }

    protected void completeRefreshData() {
        pageName++;
        mAdapter.notifyDataSetChanged();
        if (null != swipeRefreshLayout) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
