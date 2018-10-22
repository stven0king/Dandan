package com.dandan.love.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.R;
import com.dandan.love.Router;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.AuthorModel;
import com.dandan.love.bean.AuthorZiliaoModel;
import com.dandan.love.bean.AuthorZiliaoOptModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.image.ImageLoader;
import com.dandan.love.common.network.SimpleSubscriber;
import com.dandan.love.common.network.task.GSCAuthorDetailTask;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/8/29 上午10:03
 * Description: 诗人详情页
 */
public class AuthorDetailActivity extends BaseActivity{
    private ImageView userIconIV;
    private TextView userInfoTV, userChaidaiTV, userNameTV, userCountTV;
    private RecyclerView recyclerView;
    private BaseRecycleAdapter<RecycleItemEntity<AuthorZiliaoOptModel>> mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private Toolbar toolbar;
    private AuthorModel authorModel;
    private List<RecycleItemEntity<AuthorZiliaoOptModel>> listData = new ArrayList<>();
    public static String DATA = "DATA";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_detail_layout);
        if (null == getIntent().getSerializableExtra(DATA) ||
                !(getIntent().getSerializableExtra(DATA) instanceof AuthorModel)) {
            return;
        }
        authorModel = (AuthorModel) getIntent().getSerializableExtra(DATA);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        userIconIV = findViewById(R.id.user_icon);
        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back_arrow);
        toolbar.setNavigationOnClickListener(v -> finish());
        userChaidaiTV = findViewById(R.id.user_chaodai);
        userNameTV = findViewById(R.id.user_name);
        userCountTV = findViewById(R.id.user_count);
        userInfoTV = findViewById(R.id.user_info);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initData() {
        ImageLoader.loadImageCircleIcon(authorModel.getIcon(), userIconIV);
        userInfoTV.setText(Html.fromHtml(authorModel.getIntro()));
        userChaidaiTV.setText(authorModel.getChaodai());
        userNameTV.setText(authorModel.getName());
        userCountTV.setText(authorModel.getCount() + "篇");
        userCountTV.setOnClickListener(v -> {
            Router.startAuthorGushiwenListActivity(mContext, authorModel.getName());
        });
        gridLayoutManager = new GridLayoutManager(mContext, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new BaseRecycleAdapter<RecycleItemEntity<AuthorZiliaoOptModel>>(listData) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_grid_author_detail);
            }

            @Override
            protected void convert(BaseViewHolder holder, RecycleItemEntity<AuthorZiliaoOptModel> item) {
                int currentType = holder.getItemViewType();
                if (BaseRecycleAdapter.TYPE_DATA == currentType) {
                    AuthorZiliaoOptModel model = item.getData();
                    holder.setText(R.id.ziliao_title, model.getTitle());
                    //TextView ziliao = holder.getView(R.id.user_ziliao);
                    //StringBuilder builder = new StringBuilder();
                    //for (int i = 0; i < model.getAuthorZiliaoModels().size(); i++) {
                    //    builder.append("●").append(model.getAuthorZiliaoModels().get(i).getTitle());
                    //    if (i + 1 != model.getAuthorZiliaoModels().size()) {
                    //        builder.append("\n");
                    //    }
                    //}
                    //ziliao.setText(builder.toString());
                }
            }
        };
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> Router.
                startAuthorZiliaoDrawerLayoutActivity(mContext,
                        listData.get(position).getData(),
                        authorModel));

    }

    private void initEvent() {
        Subscription s = submitForObservable(new GSCAuthorDetailTask(authorModel.getName()))
                .subscribe(new SimpleSubscriber<List<AuthorZiliaoModel>>(){
                    @Override
                    public void onNext(List<AuthorZiliaoModel> authorZiliaoModels) {
                        super.onNext(authorZiliaoModels);
                        if (null != authorZiliaoModels) {
                            for (AuthorZiliaoModel model: authorZiliaoModels) {
                                AuthorZiliaoOptModel authorZiliaoOptModel = new AuthorZiliaoOptModel();
                                authorZiliaoOptModel.setModel(model);
                                authorZiliaoOptModel.setTitle(model.getTitle());
                                List<AuthorZiliaoModel> modelArrayList = model.optAuthorZiliaoModel();
                                authorZiliaoOptModel.setAuthorZiliaoModels(modelArrayList);
                                authorZiliaoOptModel.setAuthorName(authorModel.getName());
                                listData.add(new RecycleItemEntity<>(authorZiliaoOptModel));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
        addSubscription(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_count:
                break;
            default:
                break;
        }
    }
}
