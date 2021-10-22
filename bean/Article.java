package com.heyu.bean;

import lombok.Data;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 16:11:00
 */

public class Article {
    //标签
    private String tage;

    //文章名字
    private String articleName;

    //文章url
    private String articleUrl;

    @Override
    public String toString() {
        return "Article{" +
                "tage='" + tage + '\'' +
                ", articleName='" + articleName + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                '}';
    }

    public String getTage() {
        return tage;
    }

    public void setTage(String tage) {
        this.tage = tage;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public Article() {
    }

    public Article(String tage, String articleName, String articleUrl) {
        this.tage = tage;
        this.articleName = articleName;
        this.articleUrl = articleUrl;
    }
}
