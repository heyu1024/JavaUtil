package com.heyu.bean;


import java.util.List;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 14:49:00
 */
public class DataJson2 {

    /**
     * List<ChildrenFirst>--每个数组元素对应每个主题下的所有数据
     */
    private List<Theme> dataJson;

    @Override
    public String toString() {
        return "DataJson2{" +
                "dataJson=" + dataJson +
                '}';
    }

    public List<Theme> getDataJson() {
        return dataJson;
    }

    public void setDataJson(List<Theme> dataJson) {
        this.dataJson = dataJson;
    }

    public DataJson2() {
    }

    public DataJson2(List<Theme> dataJson) {
        this.dataJson = dataJson;
    }
}

