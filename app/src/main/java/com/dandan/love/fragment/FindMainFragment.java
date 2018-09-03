package com.dandan.love.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.App;
import com.dandan.love.R;
import com.dandan.love.activity.MainActivity;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.bean.BaiDuImageModel;
import com.dandan.love.bean.FindImageModel;
import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.bean.YYHuaBanModel;
import com.dandan.love.common.activity.BaseWebViewActivity;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.network.SimpleSubscriber;
import com.dandan.love.common.network.task.BaiduImageGetListTask;
import com.dandan.love.common.network.task.GankDataGetListTask;
import com.dandan.love.common.network.task.YyHuabanDataGetTask;
import com.dandan.love.common.view.NetWorkErrorLayout;
import com.dandan.love.config.GlideApp;
import com.dandan.love.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午10:47
 * Description:
 */
@SuppressLint("ValidFragment")
public class FindMainFragment extends BaseLazyFragment{
    private MainActivity activity;
    private RecyclerView mRecyclerView;
    private NetWorkErrorLayout netWorkErrorLayout;
    private BaseRecycleAdapter<RecycleItemEntity<FindImageModel>> mAdapter;

    private int mDisplayWidth;

    private int showErrorLayout = 1;

    List<RecycleItemEntity<FindImageModel>> data = new ArrayList<>();

    public FindMainFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateVie");
        View view = inflater.inflate(R.layout.fragment_find_main_layout, container, false);
        netWorkErrorLayout = view.findViewById(R.id.network_error_layout);
        netWorkErrorLayout.setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        initView();
        mRecyclerView.setAdapter(mAdapter);
        initdata();
        return view;
    }

    private void initView() {
        mDisplayWidth = DensityUtil.gettDisplayWidth(App.getApp());
        final int widht = (mDisplayWidth - 100) / 2;
        final int height = (int) (widht * 0.618 / 0.382);
        mAdapter = new BaseRecycleAdapter<RecycleItemEntity<FindImageModel>>(data) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_find_main);
            }

            @Override
            protected void convert(BaseViewHolder holder, RecycleItemEntity<FindImageModel> item) {
                switch (holder.getItemViewType()) {
                    case BaseRecycleAdapter.TYPE_DATA:
                        int position = holder.getLayoutPosition();
                        FindImageModel model = item.getData();
                        holder.setText(R.id.tv_pos, TextUtils.isEmpty(model.getDesc()) ? "" : model.getDesc());
                        ImageView imageView = holder.getView(R.id.iv_animal);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                        params.width = widht;
                        params.height = height;
                        imageView.setLayoutParams(params);
                        GlideApp.with(getActivity()).load(model.getSmallPicUrl()).into(imageView);
                        break;
                }
            }
        };

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                final RecycleItemEntity<FindImageModel> entity = mAdapter.getData().get(i);
                new ImagePreviewFragment(getActivity().getSupportFragmentManager()).open().show(entity.getData().getSourceUrl());
            }
        });
    }

    @Override
    protected void lazyLoad() {
        Logger.d(TAG, "lazyLoad");
        initdata();
    }

    private void initdata() {
        initGankioEvent();
        //initBaiduImageEvent();
    }

    private void initGankioEvent() {
        Subscription s = submitForObservable(new GankDataGetListTask(GankDataGetListTask.CLASSIFY_TYPE[0], 1))
                .subscribe(new SimpleSubscriber<ArrayList<GankIOClassifyModel>>() {
                    @Override
                    public void onNext(ArrayList<GankIOClassifyModel> list) {
                        if (null != list && list.size() > 0) {
                            for (GankIOClassifyModel tmp:list) {
                                FindImageModel model = tmp.parseFindImageModel();
                                data.add(new RecycleItemEntity<>(model));

                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showErrorLayout--;
                        checkErrorLayout();
                    }
                });
        addSubscription(s);
    }

    private void initBaiduImageEvent() {
        Subscription s1 = submitForObservable(new BaiduImageGetListTask(0, "刘亦菲"))
                .subscribe(new SimpleSubscriber<ArrayList<BaiDuImageModel>>() {
                    @Override
                    public void onNext(ArrayList<BaiDuImageModel> list) {
                        if (null != list && list.size() > 0) {
                            for (BaiDuImageModel tmp:list) {
                                FindImageModel model = tmp.parseFindImageModel();
                                data.add(new RecycleItemEntity<>(model));

                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showErrorLayout--;
                        checkErrorLayout();
                    }
                });
        addSubscription(s1);
    }

    private void initYiYuanHuaBanEvent() {
        Subscription s = submitForObservable(new YyHuabanDataGetTask(0, "34"))
                .subscribe(new SimpleSubscriber<ArrayList<YYHuaBanModel>>(){
                    @Override
                    public void onNext(ArrayList<YYHuaBanModel> list) {
                        super.onNext(list);
                        if (null != list && list.size() > 0) {
                            for (YYHuaBanModel tmp:list) {
                                FindImageModel model = new FindImageModel();
                                model.setSourceUrl(tmp.getThumb());
                                model.setSmallPicUrl(tmp.getThumb());
                                model.setBigPicUrl(tmp.getUrl());
                                model.setDesc(tmp.getTitle());
                                data.add(new RecycleItemEntity<>(model));

                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showErrorLayout--;
                        checkErrorLayout();
                    }
                });
        addSubscription(s);
    }

    private void checkErrorLayout() {
        if (showErrorLayout == 0) {
            mRecyclerView.setVisibility(View.GONE);
            netWorkErrorLayout.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            netWorkErrorLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.network_error_layout:
                showErrorLayout = 2;
                checkErrorLayout();
                initdata();
                break;
        }
    }
}
