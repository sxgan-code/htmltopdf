package com.daniel.fm.util;

import com.daniel.fm.controller.config.BaseConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;


public class CreateHtmlUtils {


    private static final Logger logger = LoggerFactory.getLogger(CreateHtmlUtils.class);

    private static String localPath;

    private static String htmlPath;


    public CreateHtmlUtils(){
        if(StringUtils.isEmpty(localPath) || StringUtils.isEmpty(htmlPath)){
            BaseConfig baseConfig = SpringContextUtil.getBean(BaseConfig.class);
            localPath = baseConfig.getLocalBasePath();
            htmlPath = baseConfig.getHtmlPath();
        }
    }

    /**
     * 将解析ftl并且赋值之后的文件内容返回流
     * @param templateName 模板文件名
     * @param root 数据Map
     * @return
     */
    public static InputStream printInputStream(String templateName, Map<String,Object> root) throws UnsupportedEncodingException {
        StringWriter out = new StringWriter();
        //设置模版文件的路径
        String ftlPath = localPath + "resources\\temp\\";
        try {
            //创建fm的配置
            Configuration config = new Configuration();
            //指定默认编码格式
            config.setDefaultEncoding("UTF-8");
            ftlPath = ftlPath.replaceAll("\\\\", "/").replaceAll("//", "/");

            logger.info("开始读取ftl流,路径=>" + ftlPath);
            config.setDirectoryForTemplateLoading(new File(ftlPath));
            //获得模版包
            Template template = config.getTemplate(templateName);
            template.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取ftl流失败,路径=>{}" + ftlPath,e);
        } catch (TemplateException e) {
            e.printStackTrace();
            logger.error("读取ftl流失败,路径=>{}" + ftlPath,e);
        } finally {
            try {
                if(out!=null) out.close();
                logger.info("读取ftl流成功,路径=>" + ftlPath);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("读取ftl流失败,路径=>{}" + ftlPath,e);
            }
        }

        //返回输入流(设置编码集为UTF-8)
        InputStream is = new ByteArrayInputStream(out.toString().getBytes("UTF-8"));
        return is;
    }


    /**
     * 创建文件
     * @param fileName  文件名称
     * @param inputStream   流
     * @return  是否创建成功，成功则返回true
     */
    public String createFile(String fileName,InputStream inputStream){
        Boolean bool = false;
        Properties properties = new Properties();
        InputStream propertiesIn = this.getClass().getResourceAsStream("/application.properties");
        // 使用InPutStream流读取properties文件
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(propertiesIn, "UTF-8"));
            properties.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取key对应的value
//        String htmlPath = properties.getProperty("HtmlPath");
       
        // 判断目录是否存在，不存在就创建目录
        File htmlDir = new File(htmlPath);
        
        if (!htmlDir.exists()){
            htmlDir.mkdirs();
        }
        String ftlPath = htmlDir+File.separator+fileName;
//        URL classPathDir = CreateHtmlUtils.class.getResource("/");
//        ftlPath = ftlPaths +File.separator+ fileName;//文件路径+名称.文件类型

        ftlPath = ftlPath.replaceAll("\\\\", "/").replaceAll("//", "/");
        
        logger.info("准备开始生成HTML模板,路径为=>" + ftlPath);

        File file = new File(ftlPath);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                //创建文件成功后，写入内容到文件里
                if(inputStream!=null){
                    logger.info("该文件存在并且输入流准备就绪，准备生成模板");
                    InputStreamToFile(inputStream, ftlPath);
                }
            }else {
                file.delete();
                file.createNewFile();
                bool = true;
                //创建文件成功后，写入内容到文件里
                if(inputStream!=null){
                    logger.info("该文件不存在并且输入流准备就绪，开始删除原文件，准备生成新模板");
                    InputStreamToFile(inputStream, ftlPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成模板失败，路径=>{}",ftlPath,e);
        }
        return ftlPath;

    }



    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @return
     * @throws IOException
     */
    public void InputStreamToFile(InputStream inputStream,String filepath) throws IOException{
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
	    //获取网络输入流
        bis = new BufferedInputStream(inputStream);
	    //建立文件
        fos = new FileOutputStream(filepath);
        //保存文件
        while ( (size = bis.read(buf)) != -1){
            fos.write(buf, 0, size);
        }
        fos.close();
        bis.close();
        logger.info("生成模板成功，路径=>" + filepath);
    }
}