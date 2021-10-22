package com.heyu.main;

import com.alibaba.fastjson.JSONObject;
import com.heyu.bean.*;
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
public class GetExcel3 {

    private static List<Theme> dataJsonList=new ArrayList<>();
    private static Set<String> themeSet=new HashSet<String>();
    private static Map<String,Set<String>> tagTypeMap=new HashMap<>();


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

        DataJson2 dataJson2 = new DataJson2(dataJsonList);
        String jsonString = JSONObject.toJSONString(dataJson2);
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
           List<Article> articles = new ArrayList<>();
           Article article = new Article();
           TagType tagTypeObj = new TagType();
           List<TagType> tagTypes = new ArrayList<>();
           Theme themeObj = new Theme();
           //放置数据
           article.setTage(tag);
           article.setArticleName(articleName);
           article.setArticleUrl(articleUrl);
           articles.add(article);
           tagTypeObj.setTagType(tagType);
           tagTypeObj.setArticles(articles);
           tagTypes.add(tagTypeObj);
           themeObj.setTheme(theme);
           themeObj.setTagTypes(tagTypes);
           //加入到dataJson
           dataJsonList.add(themeObj);
       }else {
           //存在该主题，但是不存在该标签分类
           //不存在，直接新建标签分类，然后放置数据
           if(!isTagType(theme,tagType)){
               Theme themeObjNeed = returnTheme(theme);
               List<TagType> tagTypes = themeObjNeed.getTagTypes();

               TagType tagTypeObj = new TagType();
               List<Article> articles = new ArrayList<>();
               Article article = new Article();

               article.setTage(tag);
               article.setArticleName(articleName);
               article.setArticleUrl(articleUrl);
               articles.add(article);
               tagTypeObj.setTagType(tagType);
               tagTypeObj.setArticles(articles);
               tagTypes.add(tagTypeObj);

               themeObjNeed.setTagTypes(tagTypes);
           }
           //存在该主题，也存在该标签分类
           //存在，直接放置数据
           else {
               Theme themeObjNeed = returnTheme(theme);

               List<TagType> tagTypes = themeObjNeed.getTagTypes();

               TagType typeNeed=null;
               for (TagType type : tagTypes) {
                   typeNeed=type;
                   String tagTypeStr = typeNeed.getTagType();
                   if(Objects.equals(tagTypeStr,tagType)){
                       break;
                   }
               }

               List<Article> articles = typeNeed.getArticles();
               Article article = new Article();
               article.setTage(tag);
               article.setArticleName(articleName);
               article.setArticleUrl(articleUrl);
               articles.add(article);
               typeNeed.setArticles(articles);

           }
       }
    }


    /**
     * 返回 该主题
     */
    public static Theme returnTheme(String theme){
        Theme themeObjNeed=null;
        for (Theme themeObj : dataJsonList) {
            themeObjNeed=themeObj;
            String themeStr = themeObj.getTheme();
            if(Objects.equals(themeStr,theme)){
                break;
            }
        }
        
        return themeObjNeed;
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
