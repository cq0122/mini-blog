package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogSearchUrlTest {

    @Test
    public void searchUsesQueryParametersInsteadOfKeywordPathSegments() throws Exception {
        String controller = read("src/main/java/com/site/blog/controller/blog/MyBlogController.java");
        String searchScript = read("src/main/resources/static/blog/plugins/search/search.js");
        String keywordList = read("src/main/resources/templates/blog/amaze/keyword-list.html");

        assertTrue(controller.contains("@GetMapping(value = \"/search\", params = \"keyword\")"));
        assertTrue(controller.contains("@RequestParam(\"keyword\") String keyword"));
        assertTrue(controller.contains("Math.max(1, pageNum)"));
        assertTrue(controller.contains("request.setAttribute(\"searchPage\", true)"));

        assertTrue(searchScript.contains("'/search?keyword=' + encodeURIComponent(q)"));
        assertFalse(searchScript.contains("'/search/' + q"));

        assertTrue(keywordList.contains("@{/search(keyword=${keyword}, pageNum=${blogPageResult.currPage-1})}"));
        assertTrue(keywordList.contains("${searchPage == true}"));
        assertFalse(keywordList.contains("'/' + ${pageUrl} + '/' + ${keyword} + '/' + ${blogPageResult.currPage+1}"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
