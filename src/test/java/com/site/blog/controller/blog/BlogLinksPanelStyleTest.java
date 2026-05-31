package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogLinksPanelStyleTest {

    @Test
    public void linksPanelUsesAboutStyleLeftAccent() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains(".links-page .links-list {"));
        assertTrue(baseCss.contains(".links-page .links-list:before"));
        assertTrue(baseCss.contains("background: linear-gradient(180deg, var(--home-accent), #0f766e);"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
