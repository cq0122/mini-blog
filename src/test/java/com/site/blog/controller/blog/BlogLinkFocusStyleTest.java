package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogLinkFocusStyleTest {

    @Test
    public void allBlogLinksRemoveClickFocusFrame() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains("body#blog a:focus,"));
        assertTrue(baseCss.contains("body#blog a:active {"));
        assertTrue(baseCss.contains("-webkit-box-shadow: none !important;"));
        assertTrue(baseCss.contains("box-shadow: none !important;"));
        assertTrue(baseCss.contains("outline: none !important;"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
