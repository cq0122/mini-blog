package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;

public class BlogTemplateStaticAssetsTest {

    @Test
    public void templatesDoNotReferenceMissingBaiduScript() throws Exception {
        assertFalse(read("src/main/resources/templates/blog/amaze/header.html").contains("/blog/plugins/baidu.js"));
        assertFalse(read("src/main/resources/templates/blog/amaze/detail.html").contains("/blog/plugins/baidu.js"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
