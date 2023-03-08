package com.daniel.fm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class HtmlToPdfApplication {

    private static final Logger logger = LoggerFactory.getLogger(HtmlToPdfApplication.class);
    
    public static void main(String[] args) {
        try {
            String basePath = new File("./").getCanonicalPath();

            // 对于windows 需要修正一下目录名
            if (!basePath.startsWith("/")) {
                basePath = "/" + basePath;
            }

            logger.info("base path: {}", basePath);
            System.setProperty("spring.app.home-path", basePath);
            SpringApplication.run(HtmlToPdfApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
