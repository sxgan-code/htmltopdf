package com.daniel.fm.util;

import com.daniel.fm.controller.config.BaseConfig;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class HtmlToPdfUtils {
    
    
    private final Logger logger = LoggerFactory.getLogger(HtmlToPdfUtils.class);

    private static String localPath;

    private static String pdfPathDir;

    public HtmlToPdfUtils(){
        if(StringUtils.isEmpty(localPath) || StringUtils.isEmpty(pdfPathDir)){
            BaseConfig baseConfig = SpringContextUtil.getBean(BaseConfig.class);
            localPath = baseConfig.getLocalBasePath();
            pdfPathDir = baseConfig.getPdfPath();
        }
    }

    public String htmlToPdf(String htmlPath,String pdfName){
        Properties properties = new Properties();
        try {
            InputStream propertiesIn = this.getClass().getResourceAsStream("/application.properties");
            properties.load(propertiesIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String pdfPathDir = properties.getProperty("PDFPath");

        File pdfPath = new File(pdfPathDir);
        if (!pdfPath.exists()) {
            pdfPath.mkdirs();
        }
        String pdfFile = pdfPathDir+pdfName;
        try (OutputStream os = new FileOutputStream(pdfFile)) { // 输出的pdf
            // pdf渲染器
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            
            // 字体处理
            //下面这个方法是要自己指定 字体文件   不然转出的pdf文件中 中文会变成####
            String fontDir = localPath + "/resources/fonts"+File.separator;
            String sysType = System.getProperty("os.name");
            Path fontDirectory = null;
            if (sysType.toUpperCase(Locale.ROOT).contains("win".toUpperCase(Locale.ROOT))) {
                fontDirectory = Paths.get(fontDir.substring(1));
            } else {
                fontDirectory = Paths.get(fontDir);
            }
            // PERF: Should only be called once, as each font must be parsed for font family name.
            List<AutoFont.CSSFont> fonts = AutoFont.findFontsInDirectory(fontDirectory);
            // Use this in your template for the font-family property.
            String fontFamily = AutoFont.toCSSEscapedFontFamily(fonts);
            // Add fonts to builder.
            AutoFont.toBuilder(builder, fonts);
            logger.info("字体族:[{}]",fontFamily);
            // 图片资源处理
//            builder.withHtmlContent("", baseDir + "/base-uri.htm");
            // 处理Html5中的特殊字符
            Document doc = html5ParseDocument("file:///"+htmlPath);
//            builder.withUri("file:///"+htmlPath);
            builder.withW3cDocument(doc,htmlPath);
            
            
            builder.toStream(os);
            builder.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfFile;
    }
    public static Document html5ParseDocument(String urlStr) throws IOException
    {
        URL url = new URL(urlStr);
        org.jsoup.nodes.Document doc;
        
        if (url.getProtocol().equalsIgnoreCase("file")) {
            doc = Jsoup.parse(new File(url.getPath()), "UTF-8");
        }
        else {
            doc = Jsoup.parse(url, 5000);
        }
        // Should reuse W3CDom instance if converting multiple documents.
        return new W3CDom().fromJsoup(doc);
    }
}
