package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogPageVerticalSpacingTest {

    @Test
    public void linksAndAboutPagesUseConsistentTopAndBottomSpacing() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");
        String linkTemplate = read("src/main/resources/templates/blog/amaze/link.html");
        String aboutTemplate = read("src/main/resources/templates/blog/amaze/about.html");

        assertTrue(linkTemplate.contains("page-shell links-page"));
        assertTrue(aboutTemplate.contains("page-shell about-page"));
        assertTrue(baseCss.contains("padding: 38px 14px;"));
        assertTrue(baseCss.contains("padding: 22px 12px;"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
