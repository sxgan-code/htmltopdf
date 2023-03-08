package com.daniel.fm.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseConfig {

    @Value("${path.local}")
    private String localBasePath;

    @Value("${HtmlPath}")
    private String htmlPath;

    @Value("${PDFPath}")
    private String pdfPath;

    public String getLocalBasePath() {
        return localBasePath;
    }

    public void setLocalBasePath(String localBasePath) {
        this.localBasePath = localBasePath;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }
}
