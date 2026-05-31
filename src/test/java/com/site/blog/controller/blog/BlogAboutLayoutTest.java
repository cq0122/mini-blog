package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogAboutLayoutTest {

    @Test
    public void aboutPageStacksOriginalCardAboveMapCard() throws Exception {
        String aboutTemplate = read("src/main/resources/templates/blog/amaze/about.html");
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");

        assertTrue(aboutTemplate.contains("class=\"am-u-lg-offset-1 am-u-lg-10 am-u-sm-12\""));
        assertTrue(aboutTemplate.contains("class=\"about-card page-panel\""));
        assertTrue(aboutTemplate.contains("class=\"about-card-avatar\""));
        assertTrue(aboutTemplate.contains("th:src=\"@{${configurations.get('sysAuthorImg')}}\""));
        assertTrue(aboutTemplate.contains("class=\"about-map-panel page-panel\""));
        assertTrue(aboutTemplate.contains("about-map.png"));
        assertTrue(aboutTemplate.contains("85后大叔，迷失在自己的代码中无法自拔~~~"));
        assertTrue(aboutTemplate.contains("<section class=\"contact-list\">"));
        assertFalse(aboutTemplate.contains("about-intro-grid"));
        assertTrue(baseCss.contains(".about-layout"));
        assertTrue(baseCss.contains(".about-card-avatar"));
        assertTrue(baseCss.contains(".about-page .about-card:before"));
        assertTrue(baseCss.contains(".about-map-panel"));
        assertTrue(baseCss.contains("grid-template-columns: repeat(3, minmax(0, 1fr));"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
