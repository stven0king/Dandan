package com.dandan.love.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dandan.love.R;
import com.dandan.love.base.BaseActivity;
import com.dandan.love.common.view.DDWebView;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/24 下午11:22
 * Description:
 */
public class BaseWebViewActivity extends BaseActivity{
    private Toolbar toolbar;
    private DDWebView webView;
    private String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_webview_layout);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        url = getIntent().getStringExtra("url");
        webView = findViewById(R.id.webview);
        webView.loadUrl(url);
    }

    public static void startActivituy(Context context, String url) {
        Intent intent = new Intent(context, BaseWebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
