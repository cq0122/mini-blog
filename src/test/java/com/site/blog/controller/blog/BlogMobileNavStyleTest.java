package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogMobileNavStyleTest {

    @Test
    public void mobileExpandedNavDoesNotUseStandaloneBackground() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains("body#blog .navbar-custom .navbar-collapse"));
        assertTrue(baseCss.contains("background: transparent;"));
        assertTrue(baseCss.contains("box-shadow: none;"));
        assertTrue(baseCss.contains("body#blog .navbar-custom #toggle-items .nav li a"));
        assertTrue(baseCss.contains("text-align: right;"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
