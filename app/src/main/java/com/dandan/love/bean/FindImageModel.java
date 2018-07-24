package com.dandan.love.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/20 上午9:03
 * Description:
 */
public class FindImageModel implements Serializable{
    private static final long serialVersionUID = -5522042055044161646L;
    private String sourceUrl;
    private String smallPicUrl;
    private String bigPicUrl;
    private String desc;

    public FindImageModel() {
    }

    public FindImageModel(String sourceUrl, String desc) {
        this.sourceUrl = sourceUrl;
        this.desc = desc;
    }

    public String getSmallPicUrl() {
        if (!TextUtils.isEmpty(smallPicUrl)) {
            return smallPicUrl;
        }
        if (!TextUtils.isEmpty(bigPicUrl)) {
            return bigPicUrl;
        }
        if (!TextUtils.isEmpty(sourceUrl)) {
            return sourceUrl;
        }
        return "";
    }

    public void setSmallPicUrl(String smallPicUrl) {
        this.smallPicUrl = smallPicUrl;
    }

    public String getBigPicUrl() {
        if (!TextUtils.isEmpty(bigPicUrl)) {
            return bigPicUrl;
        }
        if (!TextUtils.isEmpty(sourceUrl)) {
            return sourceUrl;
        }
        if (!TextUtils.isEmpty(smallPicUrl)) {
            return smallPicUrl;
        }
        return "";
    }

    public void setBigPicUrl(String bigPicUrl) {
        this.bigPicUrl = bigPicUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSourceUrl() {
        if (!TextUtils.isEmpty(sourceUrl)) {
            return sourceUrl;
        }
        if (!TextUtils.isEmpty(bigPicUrl)) {
            return bigPicUrl;
        }
        if (!TextUtils.isEmpty(smallPicUrl)) {
            return smallPicUrl;
        }
        return "";
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
