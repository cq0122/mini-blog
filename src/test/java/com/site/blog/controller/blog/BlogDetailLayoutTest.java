package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogDetailLayoutTest {

    @Test
    public void detailPageHasMinimumViewportHeight() throws Exception {
        String detailTemplate = read("src/main/resources/templates/blog/amaze/detail.html");
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(detailTemplate.contains("class=\"container detail-page\""));
        assertTrue(detailTemplate.contains("class=\"am-g blog-fixed detail-content-shell\""));
        assertTrue(detailTemplate.contains("class=\"detail-back-link\""));
        assertTrue(detailTemplate.contains("class=\"detail-back-arrow\""));
        assertTrue(detailTemplate.contains("aria-hidden=\"true\""));
        assertTrue(detailTemplate.contains("window.history.back()"));
        assertTrue(detailTemplate.contains("href=\"/\""));
        assertTrue(baseCss.contains(".detail-page"));
        assertTrue(baseCss.contains(".detail-back-row"));
        assertTrue(baseCss.contains(".detail-back-link"));
        assertTrue(baseCss.contains(".detail-back-arrow"));
        assertTrue(baseCss.contains("rgba(0, 150, 136, 0.14)"));
        assertTrue(baseCss.contains("linear-gradient(\n    135deg"));
        assertTrue(baseCss.contains(".detail-page {\n  width: 100%;\n  max-width: 1360px;"));
        assertTrue(baseCss.contains(".detail-page .detail-content-shell {\n  width: 100%;\n  max-width: 1360px;"));
        assertTrue(baseCss.contains(".detail-page .editormd-html-preview {\n  padding: 0;"));
        assertTrue(baseCss.contains("background: transparent;"));
        assertTrue(baseCss.contains("overflow: visible;"));
        assertTrue(baseCss.contains("min-height: max(280px, calc(100vh - 590px));"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
