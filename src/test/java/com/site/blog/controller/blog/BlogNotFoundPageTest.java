package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class BlogNotFoundPageTest {

    @Test
    public void notFoundPageReturnsHomeAfterFiveSeconds() throws Exception {
        String notFoundTemplate = read("src/main/resources/templates/error/error_404.html");

        assertTrue(notFoundTemplate.contains("<meta http-equiv=\"refresh\" content=\"5;url=/\"/>"));
        assertTrue(notFoundTemplate.contains("href=\"/\""));
        assertTrue(notFoundTemplate.contains("404.png"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
