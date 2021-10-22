package com.heyu.bean;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 19:14:00
 */
public class ResultJson {
    private String theme;
    private String tagclass;
    private String tag;
    private String article;
    private String url;

    @Override
    public String toString() {
        return "ResultJson{" +
                "theme='" + theme + '\'' +
                ", tagclass='" + tagclass + '\'' +
                ", tag='" + tag + '\'' +
                ", article='" + article + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public ResultJson() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTagclass() {
        return tagclass;
    }

    public void setTagclass(String tagclass) {
        this.tagclass = tagclass;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ResultJson(String theme, String tagclass, String tag, String article, String url) {
        this.theme = theme;
        this.tagclass = tagclass;
        this.tag = tag;
        this.article = article;
        this.url = url;
    }
}
