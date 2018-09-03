package com.dandan.love.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.R;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.AuthorZiliaoModel;
import com.dandan.love.bean.AuthorZiliaoOptModel;
import com.dandan.love.bean.RecycleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/1 下午11:52
 * Description:
 */
public class AuthorZiliaoDetailActivity extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<RecycleItemEntity<AuthorZiliaoModel>> listData = new ArrayList<>();
    public static String DATA = "DATA";
    private AuthorZiliaoOptModel authorZiliaoOptModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_ziliao_detail_layout);
        if (null == getIntent().getSerializableExtra(DATA) ||
                !(getIntent().getSerializableExtra(DATA) instanceof AuthorZiliaoOptModel)) {
            return;
        }
        authorZiliaoOptModel = (AuthorZiliaoOptModel) getIntent().getSerializableExtra(DATA);
        AuthorZiliaoModel authorModel = new AuthorZiliaoModel();
        authorModel.setTitle(authorZiliaoOptModel.getTitle());
        RecycleItemEntity recycleItemEntity = new RecycleItemEntity(authorModel);
        recycleItemEntity.setCurrentType(BaseRecycleAdapter.TYPE_HEADER);
        listData.add(recycleItemEntity);
        for (AuthorZiliaoModel model: authorZiliaoOptModel.getAuthorZiliaoModels()) {
            listData.add(new RecycleItemEntity<>(model));
        }
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle(authorZiliaoOptModel.getAuthorName());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back_arrow);
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new BaseRecycleAdapter<RecycleItemEntity<AuthorZiliaoModel>>(listData) {
            @Override
            protected void addItemTypes() {
                addItemType(BaseRecycleAdapter.TYPE_HEADER, R.layout.item_author_ziliao_title);
                addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_author_zliao_detail);
            }

            @Override
            protected void convert(BaseViewHolder holder, RecycleItemEntity<AuthorZiliaoModel> item) {
                int currentType = holder.getItemViewType();
                if (BaseRecycleAdapter.TYPE_DATA == currentType) {
                    holder.setText(R.id.ziliao_title, item.getData().getTitle());
                    TextView ziliao = holder.getView(R.id.user_ziliao);
                    ziliao.setText(Html.fromHtml(item.getData().getContext()));
                } else if (BaseRecycleAdapter.TYPE_HEADER == currentType) {
                    holder.setText(R.id.head_title, item.getData().getTitle());
                }
            }
        });
    }


}
