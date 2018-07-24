package com.dandan.love.bean;

import org.json.JSONObject;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/24 下午10:35
 * Description: 易源--花瓣
 */
public class YYHuaBanModel {
    //标题
    private String title;
    //图片地址
    private String thumb;
    //原地址链接
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static YYHuaBanModel parse(JSONObject jsonObject) {
        YYHuaBanModel model = null;
        if (null != jsonObject) {
            model = new YYHuaBanModel();
            model.thumb = jsonObject.optString("thumb");
            model.title = jsonObject.optString("title");
            model.url = jsonObject.optString("url");
        }
        return model;
    }
}
