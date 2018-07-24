package com.dandan.love.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dandan.love.R;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.base.BaseLazyFragment;
import com.dandan.love.common.logger.core.Logger;
import com.dandan.love.fragment.FindMainFragment;
import com.dandan.love.fragment.PharosMainFragment;
import com.dandan.love.fragment.WorkMainFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:07
 * Description:
 */
public class MainActivity extends BaseActivity implements OnNavigationItemSelectedListener{

    private int[] id_tabs = new int[]{R.id.tab_pharos, R.id.tab_work, R.id.tab_find};
    public String PHAROS = "PHAROS";
    public String WORK = "WORK";
    public String FIND = "FIND";
    private Map<String, Integer> mTabTag = new HashMap<>();

    private BaseLazyFragment pharosFragment;
    private BaseLazyFragment workFragment;
    private BaseLazyFragment findFragment;

    private String currentFragment;

    private Map<String, Fragment> mTabFragment = new HashMap<>();

    /**Fragment管理器.'*/
    private FragmentManager mFragmentManager;

    private BottomNavigationView navigationView;

    private Toolbar toolbar;

    private String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initView();
        initData();
    }

    private void initView() {
        navigationView = findViewById(R.id.main_navigation_view);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void initData() {
        this.mFragmentManager = this.getSupportFragmentManager();
        mTabTag.put(PHAROS, id_tabs[0]);
        mTabTag.put(WORK, id_tabs[1]);
        mTabTag.put(FIND, id_tabs[2]);
        pharosFragment = new PharosMainFragment(this);
        workFragment = new WorkMainFragment(this);
        findFragment = new FindMainFragment(this);
        mTabFragment.put(PHAROS, pharosFragment);
        mTabFragment.put(WORK, workFragment);
        mTabFragment.put(FIND, findFragment);
        navigationView.setOnNavigationItemSelectedListener(this);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragments_layout,mTabFragment.get(PHAROS))
                .show(mTabFragment.get(PHAROS)).commitAllowingStateLoss();
        currentFragment = PHAROS;
        navigationView.setSelectedItemId(id_tabs[2]);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_pharos:
                Logger.d(TAG, "tab_light_house");
                if (!PHAROS.equals(currentFragment)) {
                    switchCurrentTab(PHAROS);
                }
                return true;
            case R.id.tab_work:
                Logger.d(TAG, "tab_work");
                if (!WORK.equals(currentFragment)) {
                    switchCurrentTab(WORK);
                }
                return true;
            case R.id.tab_find:
                Logger.d(TAG, "tab_find");
                if (!FIND.equals(currentFragment)) {
                    switchCurrentTab(FIND);
                }
                return true;
        }
        return false;
    }


    public void switchCurrentTab(String tab) {
        //navigationView.setSelectedItemId(mTabTag.get(tab));
        Fragment previewFragment = mTabFragment.get(currentFragment);
        Fragment nextFragment = mTabFragment.get(tab);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(previewFragment);
        if(mTabFragment.get(tab).isAdded() == false) {
            transaction.add(R.id.fragments_layout,nextFragment);
        }
        transaction.show(nextFragment).commitAllowingStateLoss();
        //transaction.replace(R.id.fragments_layout, nextFragment).commitAllowingStateLoss();
        currentFragment = tab;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
        toolbar.setTitle(titleName);
    }
}
