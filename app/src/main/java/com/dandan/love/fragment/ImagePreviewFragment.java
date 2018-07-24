package com.dandan.love.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.dandan.love.R;
import com.dandan.love.base.BaseFragment;
import com.dandan.love.common.download.CallBack;
import com.dandan.love.common.image.DownImageImpl;
import com.dandan.love.config.GlideApp;
import com.dandan.love.config.ImageConfig;

@SuppressLint("ValidFragment")
public class ImagePreviewFragment extends BaseFragment implements View.OnClickListener{
    private String TAG;
    private FragmentManager fragmentManager;
    private boolean mIsOpen = false;
    private View contentView;
    private ViewGroup mGroup;
    private ImageView imageView;
    private String imageUrl;
    private FrameLayout parentView;
    public ImagePreviewFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.TAG = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = new FrameLayout(getActivity());
        parentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView = inflater.inflate(R.layout.fragment_image_preview_layout, null);
        contentView.setBackgroundColor(Color.rgb(0, 0, 0));
        imageView = contentView.findViewById(R.id.image);
        parentView.addView(contentView);
        mGroup = (ViewGroup) getActivity().getWindow().getDecorView();
        mGroup.addView(parentView);
        imageView.setOnClickListener(this);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DownImageImpl.saveImageToDisk(getActivity(), imageUrl,
                        System.currentTimeMillis() + ImageConfig.JPG, new CallBack() {
                    @Override
                    public boolean call(String msg) {
                        boolean flag = CallBack.SCUESS.equals(msg);
                        if (flag) {
                            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
                        }
                        return flag;
                    }
                });
                return true;
            }
        });
        GlideApp.with(this).load(imageUrl).into(this.imageView);
        mGroup.startAnimation(createTranslationInAnimation());
        imageView.startAnimation(createAlphaInAnimation());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ImagePreviewFragment show(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:
                close();
                break;
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsOpen = false;
        if (null != imageView) {
            imageView.startAnimation(createTranslationOutAnimation());
        }
        if (null != parentView) {
            parentView.startAnimation(createAlphaOutAnimation());
        }
        if (parentView != null) {
            //为了播放动画这里做了延迟
            parentView.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (mGroup != null) {
                        mGroup.removeView(parentView);
                        mGroup = null;
                        imageView = null;
                        contentView = null;
                    }
                    parentView = null;
                }
            }, 300);
        }
    }

    public ImagePreviewFragment open() {
        if (mIsOpen) {
            return null;
        }
        mIsOpen = true;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(this, TAG);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        return this;
    }

    public void close() {
        if (mIsOpen) {
            if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() != 0) {
                getFragmentManager().popBackStack();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(this);
                transaction.commitAllowingStateLoss();
                mIsOpen = false;
            }
        }
    }

    private Animation createAlphaInAnimation() {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(300);
        return an;
    }


    private Animation createAlphaOutAnimation() {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(300);
        an.setFillAfter(true);
        return an;
    }


    private Animation createTranslationInAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 1, type, 0);
        an.setDuration(300);
        return an;
    }


    private Animation createTranslationOutAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 0, type, 1);
        an.setDuration(300);
        an.setFillAfter(true);
        return an;
    }
}
