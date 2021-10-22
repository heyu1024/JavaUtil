package com.heyu.main;

import com.alibaba.fastjson.JSON;
import com.heyu.bean.Article;
import com.heyu.bean.ResultJson;
import com.heyu.bean.ResultListJson;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.*;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 12:15:00
 */
public class GetExcel02 {



    public static void main(String[] args) throws Exception {
        //Excel办公，以表格形式存在
        //如何实现：1.fastExcel 2.jxl解析（第三方） 3.poi方式（第三方）
        //通过jxl方式解析Excel步骤如下：
        //1. 导入jxl的jar包
        //2. 获取到Excel文件
        File file = new File("/Users/heyu/Desktop/4.xls");
//        System.out.println(file);
        Workbook wb = Workbook.getWorkbook(file);
        //3. 获取指定的sheet页码   通过指定的Sheet页的名字获取指定的Sheet页，也可以通过索引获取Sheet
        Sheet sheet = wb.getSheet("1");
//        System.out.println(sheet);
        //4. 获取指定的单元格的数据  通过getCell方法获取指定单元格对象，参数是column,row,索引从0开始
//		Cell cell = sheet.getCell(0,0);
//		System.out.println(cell.getContents());


        ResultListJson resultListJson = new ResultListJson();
        List<ResultJson> resultJsons=new ArrayList<>();


        //4.1 循环获取指定的行和列的单元格的值     外循环控制行，内循环控制列
        for (int i = 0; i < sheet.getRows(); i++) {
            String resultStr="";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                String contents = cell.getContents();
                resultStr+=contents+",";
//                System.out.print(contents);
            }
            String[] resultArr = resultStr.split(",");
            //获取数据
            String theme=resultArr[0].trim();
            String tagType=resultArr[1].trim();
            String tag=resultArr[2].trim();
            String articleName=resultArr[3].trim();
            String articleUrl=resultArr[4].trim();
            System.out.println();

            ResultJson resultJson = new ResultJson(theme, tagType, tag, articleName, articleUrl);
            System.out.println(resultJson);
            resultJsons.add(resultJson);
        }
        System.out.println("--------------");
        System.out.println(resultJsons);
        resultListJson.setResultJsons(resultJsons);
        String toJSONString = JSON.toJSONString(resultListJson);
        System.out.println("------------");
        System.out.println(toJSONString);
        wb.close();    //将工作簿的资源关闭
    }


}
