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
        assertTrue(baseCss.contains(".detail-page"));
        assertTrue(baseCss.contains("min-height: max(280px, calc(100vh - 590px));"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
