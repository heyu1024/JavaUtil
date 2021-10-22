package com.heyu.bean;


import java.util.List;
import java.util.Map;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 14:49:00
 */
public class DataJson {

    /**
     * List<ChildrenFirst>--每个数组元素对应每个主题下的所有数据
     */
    private List<Map<String,Map<String,List<Article>>>> dataJson;

    @Override
    public String toString() {
        return "DataJson{" +
                "dataJson=" + dataJson +
                '}';
    }

    public List<Map<String, Map<String, List<Article>>>> getDataJson() {
        return dataJson;
    }

    public void setDataJson(List<Map<String, Map<String, List<Article>>>> dataJson) {
        this.dataJson = dataJson;
    }

    public DataJson() {
    }

    public DataJson(List<Map<String, Map<String, List<Article>>>> dataJson) {
        this.dataJson = dataJson;
    }
}

