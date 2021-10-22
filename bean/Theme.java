package com.heyu.bean;


import java.util.List;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月24日 17:01:00
 */
public class Theme {

    private String theme;
    private List<TagType> tagTypes;

    @Override
    public String toString() {
        return "Theme{" +
                "theme='" + theme + '\'' +
                ", tagTypes=" + tagTypes +
                '}';
    }

    public Theme(String theme, List<TagType> tagTypes) {
        this.theme = theme;
        this.tagTypes = tagTypes;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<TagType> getTagTypes() {
        return tagTypes;
    }

    public void setTagTypes(List<TagType> tagTypes) {
        this.tagTypes = tagTypes;
    }

    public Theme() {
    }
}
