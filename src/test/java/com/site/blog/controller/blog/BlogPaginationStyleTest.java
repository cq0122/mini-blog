package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogPaginationStyleTest {

    @Test
    public void listPaginationUsesDetailCommentSelectedColor() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains(".blog-fixed .am-pagination > .active > a"));
        assertTrue(baseCss.contains("border-color: #009688;"));
        assertTrue(baseCss.contains("background: #009688;"));
        assertTrue(baseCss.contains("box-shadow: 0 10px 22px rgba(0, 150, 136, 0.22);"));
        assertTrue(baseCss.contains(".blog-fixed .am-pagination > li > a:hover"));
        assertTrue(baseCss.contains("rgba(0, 150, 136, 0.45)"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
