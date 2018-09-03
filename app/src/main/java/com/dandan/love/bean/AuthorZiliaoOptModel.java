package com.dandan.love.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/1 下午11:01
 * Description:
 */
public class AuthorZiliaoOptModel implements Serializable{
    private static final long serialVersionUID = 7064906472204008126L;
    private String title;
    private AuthorZiliaoModel model;
    private List<AuthorZiliaoModel> authorZiliaoModels;
    private String authorName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public AuthorZiliaoModel getModel() {
        return model;
    }

    public void setModel(AuthorZiliaoModel model) {
        this.model = model;
    }

    public List<AuthorZiliaoModel> getAuthorZiliaoModels() {
        return authorZiliaoModels;
    }

    public void setAuthorZiliaoModels(List<AuthorZiliaoModel> authorZiliaoModels) {
        this.authorZiliaoModels = authorZiliaoModels;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
