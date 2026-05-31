package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogThemeAccentStyleTest {

    @Test
    public void themeAccentUsesHomeArticleHoverGreenAcrossSite() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains("--home-accent: #009688;"));
        assertTrue(baseCss.contains("--home-accent-dark: #0f766e;"));
        assertTrue(baseCss.contains(".home-shell .sidebar-search input:focus"));
        assertTrue(baseCss.contains("box-shadow: 0 0 0 4px rgba(0, 150, 136, 0.1);"));
        assertTrue(baseCss.contains(".home-shell .tags a:hover"));
        assertTrue(baseCss.contains("background: rgba(0, 150, 136, 0.08);"));

        assertFalse(baseCss.contains("#2b7cff"));
        assertFalse(baseCss.contains("#1d4ed8"));
        assertFalse(baseCss.contains("rgba(43, 124, 255"));
        assertFalse(baseCss.contains("rgba(74, 122, 255"));
        assertFalse(baseCss.contains("rgba(80, 133, 255"));
        assertFalse(baseCss.contains("#eef4ff"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
