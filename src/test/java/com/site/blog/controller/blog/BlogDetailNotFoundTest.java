package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogDetailNotFoundTest {

    @Test
    public void missingBlogDetailUsesNotFoundPage() throws Exception {
        String controller = read("src/main/java/com/site/blog/controller/blog/MyBlogController.java");

        assertTrue(controller.contains("HttpServletResponse response"));
        assertTrue(controller.contains("if (blogInfo == null)"));
        assertTrue(controller.contains("response.setStatus(HttpServletResponse.SC_NOT_FOUND)"));
        assertTrue(controller.contains("return \"error/error_404\""));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
