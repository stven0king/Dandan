package com.dandan.love.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dandan.love.R;
import com.dandan.love.utils.DensityUtil;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/21 下午8:45
 * Description:
 */
public class NetWorkErrorLayout extends RelativeLayout{
    private ImageView imageView;
    private TextView textView;
    public NetWorkErrorLayout(Context context) {
        super(context);
        init();
    }

    public NetWorkErrorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NetWorkErrorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("ResourceType")
    private void init() {
        imageView = new ImageView(getContext());
        imageView.setId(0x7f070001);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                DensityUtil.dip2px(getContext(), 100), LayoutParams.WRAP_CONTENT);
        imageView.setImageResource(R.drawable.network_error_icon);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        textView = new TextView(getContext());
        RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsText.addRule(RelativeLayout.BELOW, imageView.getId());
        paramsText.topMargin = DensityUtil.dip2px(getContext(), 5);
        paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textView.setLayoutParams(paramsText);
        textView.setText("网络有问题，请点击重试");
        addView(imageView);
        addView(textView);
    }

    public void setClickListener(OnClickListener clickListener) {
        this.imageView.setOnClickListener(clickListener);
        this.textView.setOnClickListener(clickListener);

    }
}
