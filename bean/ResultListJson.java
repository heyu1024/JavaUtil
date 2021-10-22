package com.heyu.bean;

import java.util.List;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 19:13:00
 */
public class ResultListJson {
    private List<ResultJson> resultJsons;

    @Override
    public String toString() {
        return "ResultListJson{" +
                "resultJsons=" + resultJsons +
                '}';
    }

    public List<ResultJson> getResultJsons() {
        return resultJsons;
    }

    public void setResultJsons(List<ResultJson> resultJsons) {
        this.resultJsons = resultJsons;
    }

    public ResultListJson() {
    }

    public ResultListJson(List<ResultJson> resultJsons) {
        this.resultJsons = resultJsons;
    }
}
