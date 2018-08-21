package com.dandan.love.bean;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Tanzhenxing
 * Date: 2018/8/16 上午9:35
 * Description: 作者
 */
public class AuthorModel implements Serializable{
    private static final long serialVersionUID = -703937194479102711L;
    private int id;
    private String authorid;
    private String icon;
    private int count;
    private String intro;
    private String name;
    private String chaodai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChaodai() {
        return chaodai;
    }

    public void setChaodai(String chaodai) {
        this.chaodai = chaodai;
    }

    public static AuthorModel parse(JSONObject jsonObject) {
        AuthorModel authorModel = null;
        if (null != jsonObject) {
            try {
                authorModel = new AuthorModel();
                authorModel.authorid = jsonObject.optString("authorid");
                authorModel.count = jsonObject.optInt("count");
                authorModel.icon = jsonObject.optString("icon");
                authorModel.intro = jsonObject.optString("intro");
                authorModel.name = jsonObject.optString("name");
                authorModel.chaodai = jsonObject.optString("chaodai");
                authorModel.id = jsonObject.optInt("id");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return authorModel;
    }
}
