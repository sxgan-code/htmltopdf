package com.daniel.fm.controller;

import com.alibaba.fastjson.JSONObject;
import com.daniel.fm.util.CreateHtmlUtils;
import com.daniel.fm.util.HtmlToPdfUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.util.*;

@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${path.local}")
    private String localPath;

    @RequestMapping("/create")
    public String test() throws IOException {
        String  json= readJsonFileToStr();
        Map map = new Gson().fromJson(json, Map.class);
        CreateHtmlUtils createHtmlUtils = new CreateHtmlUtils();
        InputStream in = CreateHtmlUtils.printInputStream("test.ftl", map);
        logger.info("开始生成Html=======================================================================================>>>>");
        String path = createHtmlUtils.createFile("new.html", in);
        logger.info("生成HTML页面路径为：{}{}", "===>>>", path);
        HtmlToPdfUtils htmlToPdfUtils = new HtmlToPdfUtils();
        Long date = new Date().getTime();
        String pdfName = "" + date + ".pdf";
        logger.info("开始生成pdf=======================================================================================>>>>");
        String pdfPath = htmlToPdfUtils.htmlToPdf(path, pdfName);
        logger.info("生成PDF路径为：{}{}", "===>>>", pdfPath);
        return "生成成功";
    }
    /**
     * 读取json文件为String
     * @return
     */
    public String readJsonFileToStr() {
        
        File file = new File(localPath + "resources\\data\\rootData.json");
        System.out.println(file);
        String txt = null;
        if (file.exists()) {
            try {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
                BufferedReader bfr = new BufferedReader(isr);
                String line = null;
                StringBuffer buffer = new StringBuffer();
                // 循环读取,读取到最后返回null
                while ((line = bfr.readLine()) != null) {
                    buffer.append(line);
                }
                txt = buffer.toString();
                bfr.close();
                isr.close();
            } catch (IOException e) {
                txt = "";
                e.printStackTrace();
            }
        }else {
            logger.error("读取文件不存在！");
            txt = "";
        }
        return txt;
    }
    public static Map<String, Object> jsonToMap(String json){
        // json字符串转JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        HashMap<String, Object> map = new HashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            map.put(next.getKey(), next.getValue());
        }
        return map;
    }
}