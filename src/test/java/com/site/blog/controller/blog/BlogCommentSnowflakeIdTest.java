package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogCommentSnowflakeIdTest {

    @Test
    public void commentListAcceptsSnowflakeBlogIdAndDoesNotRenderInvalidData() throws Exception {
        String controller = read("src/main/java/com/site/blog/controller/blog/MyBlogController.java");
        String detailTemplate = read("src/main/resources/templates/blog/amaze/detail.html");

        assertTrue(controller.contains("listComment(AjaxPutPage<BlogComment> ajaxPutPage, Long blogId)"));
        assertFalse(controller.contains("listComment(AjaxPutPage<BlogComment> ajaxPutPage, Integer blogId)"));
        assertTrue(detailTemplate.contains("Array.isArray(res.data)"));
        assertTrue(detailTemplate.contains("safeText("));
        assertTrue(detailTemplate.contains("commentId || 0"));
        assertTrue(detailTemplate.contains("initBlogList(1, 5, '[[${blogDetailVO.blogId}]]')"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
