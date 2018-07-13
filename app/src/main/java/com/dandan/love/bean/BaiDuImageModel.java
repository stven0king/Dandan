package com.dandan.love.bean;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/12 下午11:18
 * Description:
 */
public class BaiDuImageModel implements Serializable{
    //{
    //    "adType": "0",
    //        "hasAspData": "0",
    //        "thumbURL": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=615670190,1954663761&fm=27&gp=0.jpg",
    //        "middleURL": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=615670190,1954663761&fm=27&gp=0.jpg",
    //        "largeTnImageUrl": "",
    //        "hasLarge": 0,
    //        "hoverURL": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=615670190,1954663761&fm=27&gp=0.jpg",
    //        "pageNum": 32,
    //        "objURL": "ippr_z2C$qAzdH3FAzdH3Fu5674_z&e3Bxtpjh_z&e3Bv54AzdH3Fda888aAzdH3F8mbbnAzdH3F8mbbn90AzdH3F8mbbn90_8n80cc9dn9_z&e3B3r2",
    //        "fromURL": "ippr_z2C$qAzdH3FAzdH3Fu5674_z&e3Bxtpjh_z&e3Bv54AzdH3Fu5674-etjopi6jw1-wvpt5g-r6tgpwksj-pt1-ldamnl_z&e3Bip4s",
    //        "fromURLHost": "forum.xitek.com",
    //        "currentIndex": "",
    //        "width": 1292,
    //        "height": 861,
    //        "type": "jpg",
    //        "is_gif": 0,
    //        "filesize": "",
    //        "bdSrcType": "0",
    //        "di": "33884493281",
    //        "pi": "0",
    //        "is": "0,0",
    //        "imgCollectionWord": "",
    //        "replaceUrl": [
    //    {
    //        "ObjURL": "http://img0.imgtn.bdimg.com/it/u=615670190,1954663761&fm=214&gp=0.jpg",
    //            "FromURL": "http://blog.sina.com.cn/mengluofu"
    //    },
    //    {
    //        "ObjURL": "http://forum.xitek.com/201110/172/17255/17255_1317912666.jpg",
    //            "FromURL": "http://forum.xitek.com/forum-viewthread-action-printable-tid-920639.html"
    //    }
    //],
    //    "hasThumbData": "0",
    //        "bdSetImgNum": 0,
    //        "partnerId": 0,
    //        "spn": 0,
    //        "bdImgnewsDate": "1970-01-01 08:00",
    //        "fromPageTitle": "我拍的<strong>美女</strong>家居写真一套 5d2 卡尔蔡司t* 28-85无后期",
    //        "fromPageTitleEnc": "我拍的美女家居写真一套 5d2 卡尔蔡司t* 28-85无后期",
    //        "bdSourceName": "",
    //        "bdFromPageTitlePrefix": "",
    //        "isAspDianjing": 0,
    //        "token": "",
    //        "imgType": "",
    //        "cs": "615670190,1954663761",
    //        "os": "1736032788,1894792664",
    //        "simid": "3394594037,314888761",
    //        "personalized": "0",
    //        "simid_info": null,
    //        "face_info": null,
    //        "xiangshi_info": null,
    //        "adPicId": "0",
    //        "source_type": ""
    //}

    private String thumbURL;
    private String middleURL;
    private String largeTnImageUrl;
    private String hoverURL;
    private String bdImgnewsDate;
    private String fromPageTitle;
    private String fromPageTitleEnc;
    private int width;
    private int height;

    public static BaiDuImageModel parse(JSONObject jsonObject) {
        BaiDuImageModel model = null;
        if (null != jsonObject) {
            String imageurl = jsonObject.optString("thumbURL");
            if (TextUtils.isEmpty(imageurl)) {
                return null;
            }
            model = new BaiDuImageModel();
            model.thumbURL = imageurl;
            model.middleURL = jsonObject.optString("middleURL");
            model.largeTnImageUrl = jsonObject.optString("largeTnImageUrl");
            model.hoverURL = jsonObject.optString("hoverURL");
            model.bdImgnewsDate = jsonObject.optString("bdImgnewsDate");
            model.fromPageTitle = jsonObject.optString("fromPageTitle");
            model.fromPageTitleEnc = jsonObject.optString("fromPageTitleEnc");
            model.width = jsonObject.optInt("width");
            model.height = jsonObject.optInt("height");
        }
        return model;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getMiddleURL() {
        return middleURL;
    }

    public void setMiddleURL(String middleURL) {
        this.middleURL = middleURL;
    }

    public String getLargeTnImageUrl() {
        return largeTnImageUrl;
    }

    public void setLargeTnImageUrl(String largeTnImageUrl) {
        this.largeTnImageUrl = largeTnImageUrl;
    }

    public String getHoverURL() {
        return hoverURL;
    }

    public void setHoverURL(String hoverURL) {
        this.hoverURL = hoverURL;
    }

    public String getBdImgnewsDate() {
        return bdImgnewsDate;
    }

    public void setBdImgnewsDate(String bdImgnewsDate) {
        this.bdImgnewsDate = bdImgnewsDate;
    }

    public String getFromPageTitle() {
        return fromPageTitle;
    }

    public void setFromPageTitle(String fromPageTitle) {
        this.fromPageTitle = fromPageTitle;
    }

    public String getFromPageTitleEnc() {
        return fromPageTitleEnc;
    }

    public void setFromPageTitleEnc(String fromPageTitleEnc) {
        this.fromPageTitleEnc = fromPageTitleEnc;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
