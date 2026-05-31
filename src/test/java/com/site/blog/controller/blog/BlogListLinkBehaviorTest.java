package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogListLinkBehaviorTest {

    @Test
    public void authorLinksReturnHomeAndListLinksDoNotShowFocusBox() throws Exception {
        String blogList = read("src/main/resources/templates/blog/amaze/blog-list.html");
        String keywordList = read("src/main/resources/templates/blog/amaze/keyword-list.html");
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(blogList.contains("th:href=\"@{/}\" th:text=\"${configurations.get('sysAuthor')}\""));
        assertTrue(keywordList.contains("th:href=\"@{/}\" th:text=\"${configurations.get('sysAuthor')}\""));
        assertFalse(blogList.contains("href=\"javascript:;\" th:text=\"${configurations.get('sysAuthor')}\""));
        assertFalse(keywordList.contains("href=\"javascript:;\" th:text=\"${configurations.get('sysAuthor')}\""));
        assertFalse(blogList.contains("avatar-image"));
        assertFalse(keywordList.contains("avatar-image"));
        assertFalse(blogList.contains("sysAuthorImg"));
        assertFalse(keywordList.contains("sysAuthorImg"));

        assertTrue(baseCss.contains(".home-shell .topic-header .author-lockup a:focus"));
        assertTrue(baseCss.contains(".home-shell .category-name a:focus"));
        assertTrue(baseCss.contains("outline: none;"));
        assertTrue(baseCss.contains("box-shadow: none;"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
