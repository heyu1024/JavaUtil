package com.heyu.main;

import com.alibaba.fastjson.JSONObject;
import com.heyu.bean.Article;
import com.heyu.bean.DataJson;
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
public class GetExcel {

    private static List<Map<String,Map<String,List<Article>>>> dataJson=new ArrayList<>();
    public static Set<String> themeSet=new HashSet<String>();
    public static Map<String,Set<String>> tagTypeMap=new HashMap<>();


    public static void main(String[] args) throws Exception {
        //Excel办公，以表格形式存在
        //如何实现：1.fastExcel 2.jxl解析（第三方） 3.poi方式（第三方）
        //通过jxl方式解析Excel步骤如下：
        //1. 导入jxl的jar包
        //2. 获取到Excel文件
        File file = new File("/Users/heyu/Desktop/2.xls");
//        System.out.println(file);
        Workbook wb = Workbook.getWorkbook(file);
        //3. 获取指定的sheet页码   通过指定的Sheet页的名字获取指定的Sheet页，也可以通过索引获取Sheet
        Sheet sheet = wb.getSheet("1");
//        System.out.println(sheet);
        //4. 获取指定的单元格的数据  通过getCell方法获取指定单元格对象，参数是column,row,索引从0开始
//		Cell cell = sheet.getCell(0,0);
//		System.out.println(cell.getContents());



        //4.1 循环获取指定的行和列的单元格的值     外循环控制行，内循环控制列
        for (int i = 0; i < sheet.getRows(); i++) {
            String resultStr="";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                String contents = cell.getContents();
                resultStr+=contents+",";
            }
            String[] resultArr = resultStr.split(",");
            //获取数据
            String theme=resultArr[0].trim();
            String tagType=resultArr[1].trim();
            String tag=resultArr[2].trim();
            String articleName=resultArr[3].trim();
            String articleUrl=resultArr[4].trim();



            putReuslt(theme, tagType, tag, articleName, articleUrl);

            themeSet.add(theme);
            Set<String> tagTypeSet = tagTypeMap.get(theme);
            if(tagTypeSet==null){
                Set<String> tagTypeSetNew =new HashSet<>();
                tagTypeSetNew.add(tagType);
                tagTypeMap.put(theme, tagTypeSetNew);
            }else {
                tagTypeSet.add(tagType);
                tagTypeMap.put(theme, tagTypeSet);
            }


        }

        DataJson dataJson = new DataJson(GetExcel.dataJson);
        String jsonString = JSONObject.toJSONString(dataJson);
        System.out.println(jsonString);

        wb.close();    //将工作簿的资源关闭
    }

    /**
     * 1. 判断主题是否存在
     *  1。1 不存在，直接新建主题和标签分类，然后放置数据
     *  1。2 存在 ，继续判断标签分类是否存在
     *          1。2。1 不存在，直接新建标签分类，然后放置数据
     *          1。2。2 存在，直接放置数据
     */
    public static void putReuslt(String theme,String tagType,String tag,String articleName,String articleUrl){
       if(!isTheme(theme)){
           //不存在该主题
           //直接新建主题和标签分类
           HashMap<String, Map<String, List<Article>>> childrenFirst = new HashMap<>();
           HashMap<String, List<Article>> childrenSecond = new HashMap<>();
           ArrayList<Article> articles = new ArrayList<>();
           Article article = new Article();
           //放置数据
           article.setTage(tag);
           article.setArticleName(articleName);
           article.setArticleUrl(articleUrl);
           articles.add(article);
           childrenSecond.put(tagType, articles);
           childrenFirst.put(theme, childrenSecond);
           //加入到dataJson
           dataJson.add(childrenFirst);
       }else {
           //存在该主题，但是不存在该标签分类
           //不存在，直接新建标签分类，然后放置数据
           if(!isTagType(theme,tagType)){
               Map<String, Map<String, List<Article>>> childrenFirstNeed = returnChildrenFirstNeed(theme);
               dataJson.remove(childrenFirstNeed);
               Map<String, List<Article>> childrenSecond = childrenFirstNeed.get(theme);
               ArrayList<Article> articles = new ArrayList<>();
               Article article = new Article();
               article.setTage(tag);
               article.setArticleName(articleName);
               article.setArticleUrl(articleUrl);
               articles.add(article);
               childrenSecond.put(tagType, articles);
               childrenFirstNeed.put(theme, childrenSecond);
               dataJson.add(childrenFirstNeed);
           }
           //存在该主题，也存在该标签分类
           //存在，直接放置数据
           else {
               Map<String, Map<String, List<Article>>> childrenFirstNeed = returnChildrenFirstNeed(theme);
               dataJson.remove(childrenFirstNeed);
               Map<String, List<Article>> childrenSecondNeed = childrenFirstNeed.get(theme);
               List<Article> articlesNeed = childrenSecondNeed.get(tagType);
               Article article = new Article();
               article.setTage(tag);
               article.setArticleName(articleName);
               article.setArticleUrl(articleUrl);
               articlesNeed.add(article);
               childrenSecondNeed.put(tagType, articlesNeed);
               childrenFirstNeed.put(theme, childrenSecondNeed);
               dataJson.add(childrenFirstNeed);
           }
       }
    }


    /**
     * 返回 该主题
     */
    public static Map<String, Map<String, List<Article>>> returnChildrenFirstNeed(String theme){
        Map<String, List<Article>> childrenSecond =null;
        Map<String, Map<String, List<Article>>> childrenFirstNeed=null;
        for (Map<String, Map<String, List<Article>>> childrenFirst : dataJson) {
            childrenFirstNeed=childrenFirst;
            childrenSecond = childrenFirst.get(theme);
            if(childrenSecond==null){
                continue;
            }else{
                break;
            }
        }

        return childrenFirstNeed;
    }


    /**
     * 1.先判断有没有该主题
     *
     * @param theme
     * @return
     */
    public static boolean isTheme(String theme) {
        return themeSet.contains(theme);
    }

    /**
     * 1。1 如果有，就进入该主题下，再判断有没有该标签分类
     *
     * @param theme
     * @return
     */
    public static boolean isTagType(String theme,String tagType) {
        Set<String> tagTypeSet = tagTypeMap.get(theme);
        return tagTypeSet.contains(tagType);
    }
}
