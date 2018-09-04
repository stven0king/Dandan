package com.dandan.love;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.dandan.love.activity.AuthorDetailActivity;
import com.dandan.love.activity.AuthorGushiwenListActivity;
import com.dandan.love.activity.AuthorZiliaoDetailActivity;
import com.dandan.love.activity.GushiwenListActivity;
import com.dandan.love.bean.AuthorModel;
import com.dandan.love.bean.AuthorZiliaoOptModel;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/1 下午11:57
 * Description:
 */
public class Router {
    public static void startAuthorDetailActicvity(Context context, @NonNull AuthorModel authorModel) {
        Intent intent = new Intent(context, AuthorDetailActivity.class);
        intent.putExtra(AuthorDetailActivity.DATA, authorModel);
        context.startActivity(intent);
    }

    public static void startAuthorZiliaoDetailActivity(Context context, @NonNull AuthorZiliaoOptModel authorZiliaoOptModel) {
        Intent intent = new Intent(context, AuthorZiliaoDetailActivity.class);
        intent.putExtra(AuthorZiliaoDetailActivity.DATA, authorZiliaoOptModel);
        context.startActivity(intent);
    }

    public static void startAuthorGushiwenListActivity(Context context, String title) {
        Intent intent = new Intent(context, AuthorGushiwenListActivity.class);
        intent.putExtra(GushiwenListActivity.TITLE, title);
        context.startActivity(intent);
    }
}
