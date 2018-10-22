package com.dandan.love.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.R;
import com.dandan.love.Router;
import com.dandan.love.activity.MainActivity;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.AuthorModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.image.GlideCircleTransform;
import com.dandan.love.common.image.ImageLoader;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.network.SimpleSubscriber;
import com.dandan.love.common.network.task.GSCAuthroPopularListTask;
import com.dandan.love.common.view.NetWorkErrorLayout;
import com.dandan.love.config.GlideApp;
import com.dandan.love.utils.DensityUtil;
import com.dandan.love.view.viewholder.decoration.RecycleViewLinearDivider;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 下午2:33
 * Description:
 */
@SuppressLint("ValidFragment")
public class WorkMainFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NetWorkErrorLayout netWorkErrorLayout;
    private BaseRecycleAdapter<RecycleItemEntity<AuthorModel>> mAdapter;

    private List<RecycleItemEntity<AuthorModel>> listData = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private int pageName = 1;
    public WorkMainFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_main_layout, container, false);
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
        mAdapter = new BaseRecycleAdapter<RecycleItemEntity<AuthorModel>>(listData) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_work_main);
            }

            @Override
            protected void convert(BaseViewHolder holder, RecycleItemEntity<AuthorModel> item) {
                int currentType = holder.getItemViewType();
                if (BaseRecycleAdapter.TYPE_DATA == currentType) {
                    AuthorModel model = item.getData();
                    ImageView imageView = holder.getView(R.id.user_icon);
                    holder.setText(R.id.user_name, model.getName());
                    holder.setText(R.id.user_chaodai, model.getChaodai());
                    holder.setText(R.id.user_name, model.getName());
                    if (!TextUtils.isEmpty(model.getIcon())) {
                        ImageLoader.loadImageCircleIcon(model.getIcon(), imageView);
                    } else {

                    }
                    TextView textView = holder.getView(R.id.user_ziliao);
                    textView.setText(Html.fromHtml(model.getIntro()));
                }
            }
        };

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new RecycleViewLinearDivider(mContext, LinearLayoutManager.VERTICAL,
                R.drawable.recycleview_divider_line_bg));
        mAdapter.setOnItemClickListener((adapter, view1, position) ->
                Router.startAuthorDetailActicvity(mContext, listData.get(position).getData()));
        initData();
        return view;
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

    private void initData() {
        Subscription s = submitForObservable(new GSCAuthroPopularListTask(pageName, 10))
                .subscribe(new SimpleSubscriber<List<AuthorModel>>(){
                    @Override
                    public void onNext(List<AuthorModel> list) {
                        if (pageName == 1) {
                            listData.clear();
                        }
                        if (null != list && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                listData.add(new RecycleItemEntity<>(list.get(i)));
                            }
                        }
                        pageName++;
                        mAdapter.notifyDataSetChanged();
                        completeRefreshData();
                    }
                });

        addSubscription(s);
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
        Logger.d(TAG, "onRefresh: ");
        pageName = 1;
        initData();
    }


    private void completeRefreshData() {
        if (null != swipeRefreshLayout) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
