package com.dandan.love.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.App;
import com.dandan.love.R;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.base.BaseHeaderAdapter;
import com.dandan.love.bean.GankIOClassifyModel;
import com.dandan.love.bean.PinnedHeaderEntity;
import com.dandan.love.common.network.task.GankDataGetListTask;
import com.dandan.love.listener.OnHeaderClickListener;
import com.dandan.love.utils.DensityUtil;
import com.dandan.love.utils.Utils;
import com.dandan.love.view.viewholder.decoration.PinnedHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    private BaseHeaderAdapter<PinnedHeaderEntity<String>> mAdapter;

    private int mDisplayWidth;

    List<PinnedHeaderEntity<String>> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayWidth = DensityUtil.gettDisplayHeight(App.getApp());
        mAdapter = new BaseHeaderAdapter<PinnedHeaderEntity<String>>(data) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_pinned_header);
                addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_data);
            }

            @Override
            protected void convert(BaseViewHolder holder, PinnedHeaderEntity<String> item) {
                switch (holder.getItemViewType()) {
                    case BaseHeaderAdapter.TYPE_HEADER:
                        holder.setText(R.id.tv_animal, item.getPinnedHeaderName());
                        holder.setOnClickListener(R.id.tv_animal, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        break;
                    case BaseHeaderAdapter.TYPE_DATA:

                        int position = holder.getLayoutPosition();
                        holder.setText(R.id.tv_pos, position + "");
                        ImageView imageView = holder.getView(R.id.iv_animal);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                        params.width = (mDisplayWidth - 100) / 2;
                        params.height = (position % 3 == 0) ? 500 : 660;
                        imageView.setLayoutParams(params);
                        Glide.with(MainActivity.this).load(item.getData()).into(imageView);
                        break;
                }
            }

            @Override
            public boolean isPinnedHeaderType(int viewType) {
                //return viewType == BaseHeaderAdapter.TYPE_HEADER;
                return false;
            }

            @Override
            public PinnedHeaderEntity<String> getPinnedHeaderInfo(int position) {
                return (PinnedHeaderEntity<String>) getData().get(position);
            }
        };

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                final PinnedHeaderEntity<String> entity = (PinnedHeaderEntity<String>) mAdapter.getData().get(i);
                Toast.makeText(MainActivity.this, entity.getPinnedHeaderName() + ", position " + i + ", id " + entity.getData(), Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                final PinnedHeaderEntity<String> entity = (PinnedHeaderEntity<String>) mAdapter.getData().get(i);
                Toast.makeText(MainActivity.this, "click, tag: " + entity.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        OnHeaderClickListener<PinnedHeaderEntity<Integer>> headerClickListener = new OnHeaderClickListener<PinnedHeaderEntity<Integer>>() {
            @Override
            public void onHeaderClick(int id, int position, PinnedHeaderEntity<Integer> data) {
                Toast.makeText(MainActivity.this, "click, tag: " + data.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHeaderLongClick(int id, int position, PinnedHeaderEntity<Integer> data) {
                Toast.makeText(MainActivity.this, "long click, tag: " + data.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHeaderDoubleClick(int id, int position, PinnedHeaderEntity<Integer> data) {
                Toast.makeText(MainActivity.this, "double click, tag: " + data.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
            }
        };
        //mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration.Builder<PinnedHeaderEntity<Integer>>().setDividerId(R.drawable.divider).enableDivider(true)
        //        .setHeaderClickListener(headerClickListener).create());
        mRecyclerView.setAdapter(mAdapter);
        initdata();
    }

    private void initdata() {
        Subscription s = new GankDataGetListTask(GankDataGetListTask.CLASSIFY_TYPE[0], 1)
                .exeForObservable()
                .subscribe(new Subscriber<ArrayList<GankIOClassifyModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

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
    }

}