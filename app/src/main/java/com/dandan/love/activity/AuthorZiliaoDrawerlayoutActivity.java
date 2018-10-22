package com.dandan.love.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dandan.love.R;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.AuthorModel;
import com.dandan.love.bean.AuthorZiliaoModel;
import com.dandan.love.bean.AuthorZiliaoOptModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/30 3:48 PM
 * Description: 资料详情页---抽屉布局
 */
public class AuthorZiliaoDrawerlayoutActivity extends BaseActivity {
    private NavigationView navigationView;
    private RelativeLayout headView;
    private ImageView iconIV;
    private TextView userChaodai, userName;
    private Menu menu;
    private TextView mainText;

    private List<RecycleItemEntity<AuthorZiliaoModel>> listData = new ArrayList<>();
    public static String DATA = "DATA";
    public static String AUTHOR_INFO = "AUTHOR_INFO";
    private AuthorZiliaoOptModel authorZiliaoOptModel;
    private AuthorModel authorModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_ziliao_drawer_layout);
        authorModel = (AuthorModel) getIntent().getSerializableExtra(AUTHOR_INFO);
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
        initData();
    }

    private void initView() {
        navigationView = findViewById(R.id.navigation_view);
        headView = (RelativeLayout) navigationView.getHeaderView(0);
        iconIV = headView.findViewById(R.id.user_icon);
        userChaodai = headView.findViewById(R.id.user_chaodai);
        userName = headView.findViewById(R.id.user_name);
        menu = navigationView.getMenu();
        mainText = findViewById(R.id.main_text);
    }

    private void initData() {
        ImageLoader.loadImageCircleIcon(authorModel.getIcon(), iconIV);
        userChaodai.setText(authorModel.getChaodai());
        userName.setText(authorModel.getName());
        for (AuthorZiliaoModel model: authorZiliaoOptModel.getAuthorZiliaoModels()) {
            menu.add(model.getTitle());
        }
        if (authorZiliaoOptModel.getAuthorZiliaoModels().size() > 0) {
            mainText.setText(authorZiliaoOptModel.getAuthorZiliaoModels().get(0).getContext());
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = (String) item.getTitle();

                return false;
            }
        });
    }
}
