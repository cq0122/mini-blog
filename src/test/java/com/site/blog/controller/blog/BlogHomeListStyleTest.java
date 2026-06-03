package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogHomeListStyleTest {

    @Test
    public void homeArticleListUsesQuietContentFlowStyle() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains(".home-shell .blog-entry-article:before"));
        assertTrue(baseCss.contains("background: #009688;"));
        assertTrue(baseCss.contains("transform: scaleY(0.36);"));
        assertTrue(baseCss.contains(".home-shell .blog-entry-article:hover:before"));
        assertTrue(baseCss.contains("background: linear-gradient(90deg, rgba(0, 150, 136, 0.035), transparent 48%);"));
        assertTrue(baseCss.contains(".home-shell .abstract p"));
        assertTrue(baseCss.contains(".home-shell {\n  max-width: 1360px;"));
        assertTrue(baseCss.contains("max-width: 820px;"));
        assertTrue(baseCss.contains(".home-shell .category-name a"));
        assertTrue(baseCss.contains("rgba(0, 150, 136, 0.08)"));
        assertFalse(baseCss.contains(".home-shell .blog-entry-article {\n    position: relative;\n    overflow: visible;\n    margin: 0;\n    padding: 0;\n    border: 0;\n    border-bottom: 1px solid rgba(225, 230, 239, 0.86);\n    border-radius: 0;\n    background: transparent;\n    box-shadow: none;\n    transition: background-color 0.18s ease, border-color 0.18s ease;\n}"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
