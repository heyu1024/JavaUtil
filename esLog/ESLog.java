package com.heyu.esLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年09月15日 14:11:00
 */
public class ESLog {
    public static void main(String[] args) {
        getCubeIdAndTraceId();
    }

    public static void getCubeIdAndTraceId() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/esLogInput.text"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/esOutput.text"))
        ) {
            String str = "";
            Set<String> cubeIdSet = new HashSet<>();
            Map<String,ArrayList<String>> cubeIdtraceIdList=new HashMap<>();
            String traceId = null;
            while ((str = br.readLine()) != null) {
                if(str.contains("traceId__")){
                    String[] splitTraceId = str.split("\"");
                    traceId=splitTraceId[3];
                }
                if (str.contains("cubeId")) {
                    String[] splitCubeId = str.split("cubeId");
                    String cubeIdNumberStr01 = splitCubeId[1];
                    String[] split01 = cubeIdNumberStr01.split("\"");
                    String cubeIdNumberStr02 = split01[2];
                    String[] split02 = cubeIdNumberStr02.split("\\\\");
                    //如果set中不存在该cubeId
                    if(!cubeIdSet.contains(split02[0])){
                        //把cubeId装入set集合
                        cubeIdSet.add(split02[0]);
                        ArrayList<String> traceIdList = new ArrayList<>();
                        traceIdList.add(traceId);
                        cubeIdtraceIdList.put(split02[0],traceIdList);
                    }else {
                     //如果set中已存在该cubeId
                        ArrayList<String> traceIdList = cubeIdtraceIdList.get(split02[0]);
                        traceIdList.add(traceId);
                        cubeIdtraceIdList.put(split02[0],traceIdList);
                    }

                }
            }
            System.out.println(cubeIdSet);
            System.out.println(cubeIdtraceIdList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
