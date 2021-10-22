package com.heyu.test;

import java.util.ArrayList;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月26日 11:48:00
 */
public class ListRemoveTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        for (String item : list) {
            if("2".equals(item)){
                list.remove(item);
            }
            System.out.println(list);
        }
    }
}
