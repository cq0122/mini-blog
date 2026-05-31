package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogPaginationWindowTemplateTest {

    @Test
    public void listTemplatesUsePageResultWindowInsteadOfCurrentPageOffsets() throws Exception {
        String blogList = read("src/main/resources/templates/blog/amaze/blog-list.html");
        String keywordList = read("src/main/resources/templates/blog/amaze/keyword-list.html");

        assertTrue(blogList.contains("#numbers.sequence(blogPageResult.pageStart, blogPageResult.pageEnd)"));
        assertTrue(keywordList.contains("#numbers.sequence(blogPageResult.pageStart, blogPageResult.pageEnd)"));
        assertFalse(blogList.contains("currPage-3"));
        assertFalse(keywordList.contains("currPage-3"));
        assertFalse(blogList.contains("currPage+3"));
        assertFalse(keywordList.contains("currPage+3"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
