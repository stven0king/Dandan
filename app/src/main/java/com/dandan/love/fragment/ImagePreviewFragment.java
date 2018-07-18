package com.dandan.love.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dandan.love.R;
import com.dandan.love.activity.MainActivity;
import com.dandan.love.base.BaseFragment;
import com.dandan.love.common.download.CallBack;
import com.dandan.love.common.image.DownImageImpl;
import com.dandan.love.config.FileConfig;
import com.dandan.love.config.GlideApp;
import com.dandan.love.config.ImageConfig;
import com.dandan.love.utils.FileUtils;

import java.io.File;
import java.io.IOException;

@SuppressLint("ValidFragment")
public class ImagePreviewFragment extends BaseFragment implements View.OnClickListener{
    private String TAG;
    private FragmentManager fragmentManager;
    private boolean mIsOpen = false;
    private View rootView;
    private ViewGroup mGroup;
    private ImageView imageView;
    private String imageUrl;
    public ImagePreviewFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.TAG = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_image_preview_layout, null);
        imageView = rootView.findViewById(R.id.image);
        GlideApp.with(this).load(imageUrl).into(this.imageView);
        mGroup = (ViewGroup) getActivity().getWindow().getDecorView();
        mGroup.addView(rootView);
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
        mIsOpen = false;
        if (rootView != null) {
            //为了播放动画这里做了延迟
            rootView.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (mGroup != null) {
                        mGroup.removeView(rootView);
                    }
                    rootView = null;
                }
            }, 300);
        }
        super.onDestroyView();
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
}
