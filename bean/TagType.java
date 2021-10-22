package com.heyu.bean;

import java.util.List;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月24日 17:04:00
 */
public class TagType {
    private String tagType;
    private List<Article> articles;

    @Override
    public String toString() {
        return "TagType{" +
                "tagType='" + tagType + '\'' +
                ", articles=" + articles +
                '}';
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public TagType() {
    }

    public TagType(String tagType, List<Article> articles) {
        this.tagType = tagType;
        this.articles = articles;
    }
}
