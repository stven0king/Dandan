package com.dandan.love.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dandan.love.common.image.GlideCircleTransform;
import com.dandan.love.common.network.RetrofitTask;
import com.dandan.love.config.Config;
import com.dandan.love.config.GlideApp;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:54
 * Description:
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected static String TAG;
    protected Context mContext;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mContext = this;
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    protected <T> Observable<T> submitForObservable(RetrofitTask<T> task){
        return task.exeForObservable() ;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
            this.mCompositeSubscription = null;
        }
    }

    protected void loadImage(ImageView imageView, String url) {
        GlideApp.with(this)
                .load(url)
                .into(imageView);
    }

    protected void loadCircleImage(ImageView imageView, String url) {
        GlideApp.with(this)
                .load(url)
                .transform(new GlideCircleTransform(this))
                .into(imageView);
    }
}
