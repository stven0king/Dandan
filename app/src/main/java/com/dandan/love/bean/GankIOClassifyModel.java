package com.dandan.love.bean;

import com.dandan.love.common.network.task.GankDataGetListTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:59
 * Description: gank.io api 类别
 */
public class GankIOClassifyModel implements Serializable{
    private static final long serialVersionUID = -7135223069102036776L;
    //{
    //    "_id": "5b457cf5421aa92fc7503ed9",
    //        "createdAt": "2018-07-11T11:43:49.902Z",
    //        "desc": "简洁漂亮的Android 圆角、圆形 ImageView。",
    //        "images": [
    //    "http://img.gank.io/9fb3fe13-3fbd-4e63-93b8-306ee44814bb",
    //            "http://img.gank.io/09300e19-7f73-4698-accb-c31837bba797",
    //            "http://img.gank.io/e3e244a0-2fd9-42ce-b3dc-93cf2afd7c8d",
    //            "http://img.gank.io/80435336-a4d0-4f6a-85c8-5b0d517ce6d9",
    //            "http://img.gank.io/a26bb2e0-218e-4f45-96bd-05c79fe5e34d"
    //],
    //    "publishedAt": "2018-07-11T00:00:00.0Z",
    //        "source": "chrome",
    //        "type": "Android",
    //        "url": "https://github.com/SheHuan/NiceImageView",
    //        "used": true,
    //        "who": "lijinshanmx"
    //}
    private String id;
    private String createTime;
    private String desc;
    private List<String> images;
    private String publishTime;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;



    public static GankIOClassifyModel parseFindImageModel(JSONObject jsonObject) {
        GankIOClassifyModel result = null;
        if (null != jsonObject) {
            try {
                result = new GankIOClassifyModel();
                result.id = jsonObject.optString("_id");
                result.desc = jsonObject.optString("desc");
                result.createTime = jsonObject.optString("createdAt");
                result.publishTime = jsonObject.optString("publishedAt");
                result.source = jsonObject.optString("source");
                result.type = jsonObject.optString("type");
                result.url = jsonObject.optString("url");
                result.used = jsonObject.optBoolean("used");
                result.who = jsonObject.optString("who");
                if (jsonObject.has("images")) {
                    JSONArray jsonArray = jsonObject.optJSONArray("images");
                    if (null != null && jsonArray.length() > 0) {
                        result.images = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            result.images.add(String.valueOf(jsonArray.get(i)));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public FindImageModel parseFindImageModel() {
        FindImageModel findImageModel = null;
        if (GankDataGetListTask.CLASSIFY_TYPE[0].equals(this.type)) {
            findImageModel = new FindImageModel();
            findImageModel.setDesc(this.who);
            findImageModel.setSmallPicUrl(this.url);
            findImageModel.setBigPicUrl(this.url);
            findImageModel.setSourceUrl(this.url);
        }
        return findImageModel;
    }
}
