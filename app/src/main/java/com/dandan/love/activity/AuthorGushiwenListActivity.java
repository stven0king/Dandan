package com.dandan.love.activity;

import com.dandan.love.bean.GushiwenModel;
import com.dandan.love.bean.RecycleItemEntity;
import com.dandan.love.common.network.SimpleSubscriber;
import com.dandan.love.common.network.task.GSCGushiwenListByAuthor;

import java.util.List;

import rx.Subscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/4 上午10:25
 * Description:
 */
public class AuthorGushiwenListActivity extends GushiwenListActivity{

    @Override
    public void initData() {
        Subscription s = submitForObservable(new GSCGushiwenListByAuthor(pageName, 10, title))
                .subscribe(new SimpleSubscriber<List<GushiwenModel>>() {
                    @Override
                    public void onNext(List<GushiwenModel> list) {
                        super.onNext(list);
                        for (GushiwenModel model: list) {
                            listData.add(new RecycleItemEntity<>(model));
                        }
                        completeRefreshData();
                    }
                });
        addSubscription(s);
    }
}
