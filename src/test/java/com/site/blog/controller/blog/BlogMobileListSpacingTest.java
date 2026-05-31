package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogMobileListSpacingTest {

    @Test
    public void mobileSidebarKeepsSpaceAfterPagination() throws Exception {
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(baseCss.contains("@media only screen and (max-width: 767px)"));
        assertTrue(baseCss.contains(".home-shell .sidebar-container {\n        padding: 36px 8px 0;\n    }"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
