package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CategoryRouteUsesIdTest {

    @Test
    public void categoryLinksAndPaginationUseCategoryId() throws Exception {
        String blogList = read("src/main/resources/templates/blog/amaze/blog-list.html");
        String keywordList = read("src/main/resources/templates/blog/amaze/keyword-list.html");
        String controller = read("src/main/java/com/site/blog/controller/blog/MyBlogController.java");

        assertTrue(blogList.contains("@{'/category/' + ${blog.blogCategoryId}}"));
        assertTrue(keywordList.contains("@{'/category/' + ${blog.blogCategoryId}}"));
        assertFalse(blogList.contains("@{'/category/' + ${blog.blogCategoryName}}"));
        assertFalse(keywordList.contains("@{'/category/' + ${blog.blogCategoryName}}"));

        assertTrue(controller.contains("@PathVariable(\"categoryId\") Integer categoryId"));
        assertTrue(controller.contains(".eq(BlogInfo::getBlogCategoryId, categoryId)"));
        assertTrue(controller.contains("request.setAttribute(\"keyword\", categoryId)"));
        assertTrue(controller.contains("request.setAttribute(\"keyword\", tagId)"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
