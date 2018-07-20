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
import com.dandan.love.base.BaseHeaderAdapter;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.bean.BaiDuImageModel;
import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.bean.PinnedHeaderEntity;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.common.network.SimpleSubscriber;
import com.dandan.love.common.network.task.BaiduImageGetListTask;
import com.dandan.love.common.network.task.GankDataGetListTask;
import com.dandan.love.config.GlideApp;
import com.dandan.love.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
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

    private BaseHeaderAdapter<PinnedHeaderEntity<String>> mAdapter;

    private int mDisplayWidth;

    List<PinnedHeaderEntity<String>> data = new ArrayList<>();

    public FindMainFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateVie");
        View view = inflater.inflate(R.layout.fragment_find_main_layout, container, false);
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
        mDisplayWidth = DensityUtil.gettDisplayHeight(App.getApp());
        mAdapter = new BaseHeaderAdapter<PinnedHeaderEntity<String>>(data) {
            @Override
            protected void addItemTypes() {
                //addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_pinned_header);
                addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_data);
            }

            @Override
            protected void convert(BaseViewHolder holder, PinnedHeaderEntity<String> item) {
                switch (holder.getItemViewType()) {
                    //case BaseHeaderAdapter.TYPE_HEADER:
                    //    holder.setText(R.id.tv_animal, item.getPinnedHeaderName());
                    //    holder.setOnClickListener(R.id.tv_animal, new View.OnClickListener() {
                    //        @Override
                    //        public void onClick(View v) {
                    //
                    //        }
                    //    });
                    //    break;
                    case BaseHeaderAdapter.TYPE_DATA:

                        int position = holder.getLayoutPosition();
                        holder.setText(R.id.tv_pos, item.getPinnedHeaderName());
                        ImageView imageView = holder.getView(R.id.iv_animal);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                        params.width = (mDisplayWidth - 100) / 2;
                        params.height = (position % 3 == 0) ? 500 : 660;
                        imageView.setLayoutParams(params);
                        GlideApp.with(getActivity()).load(item.getData()).into(imageView);
                        break;
                }
            }
        };

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                final PinnedHeaderEntity<String> entity = (PinnedHeaderEntity<String>) mAdapter.getData().get(i);
                new ImagePreviewFragment(getActivity().getSupportFragmentManager()).open().show(entity.getData());
            }
        });
    }

    @Override
    protected void lazyLoad() {
        Logger.d(TAG, "lazyLoad");
        initdata();
    }

    private void initdata() {
        Subscription s = new GankDataGetListTask(GankDataGetListTask.CLASSIFY_TYPE[0], 1)
                .exeForObservable()
                .subscribe(new SimpleSubscriber<ArrayList<GankIOClassifyModel>>() {
                    @Override
                    public void onNext(ArrayList<GankIOClassifyModel> list) {
                        if (null != list && list.size() > 0) {
                            for (GankIOClassifyModel tmp:list) {
                                data.add(new PinnedHeaderEntity<String>(tmp.getUrl(), BaseHeaderAdapter.TYPE_DATA, tmp.getSource()));

                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
        addSubscription(s);
        Subscription s1 = new BaiduImageGetListTask(0, "刘亦菲")
                .exeForObservable()
                .subscribe(new SimpleSubscriber<ArrayList<BaiDuImageModel>>() {
                    @Override
                    public void onNext(ArrayList<BaiDuImageModel> list) {
                        if (null != list && list.size() > 0) {
                            for (BaiDuImageModel tmp:list) {
                                String url = !TextUtils.isEmpty(tmp.getHoverURL()) ?
                                        tmp.getHoverURL() : !TextUtils.isEmpty(tmp.getMiddleURL()) ?
                                        tmp.getMiddleURL() : !TextUtils.isEmpty(tmp.getThumbURL()) ?
                                        tmp.getThumbURL() : tmp.getLargeTnImageUrl();
                                data.add(new PinnedHeaderEntity<String>(url, BaseHeaderAdapter.TYPE_DATA, tmp.getFromPageTitleEnc()));

                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
        addSubscription(s1);
    }
}
