package com.site.blog.controller.blog;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlogElasticScrollTest {

    @Test
    public void blogPagesLoadTopElasticScrollInteraction() throws Exception {
        String headerTemplate = read("src/main/resources/templates/blog/amaze/header.html");
        String detailTemplate = read("src/main/resources/templates/blog/amaze/detail.html");
        String baseCss = read("src/main/resources/static/blog/amaze/css/base.css");
        String elasticScroll = read("src/main/resources/static/blog/amaze/js/elastic-scroll.js");

        assertTrue(headerTemplate.contains("/blog/amaze/js/elastic-scroll.js"));
        assertTrue(detailTemplate.contains("/blog/amaze/js/elastic-scroll.js"));
        assertTrue(headerTemplate.contains("defer"));
        assertTrue(detailTemplate.contains("defer"));
        assertTrue(baseCss.contains("--elastic-pull-offset"));
        assertTrue(baseCss.contains("body.elastic-scroll-active"));
        assertTrue(baseCss.contains("body.elastic-scroll-return"));
        assertTrue(baseCss.contains("@media (prefers-reduced-motion: no-preference)"));
        assertTrue(elasticScroll.contains("touchstart"));
        assertTrue(elasticScroll.contains("touchmove"));
        assertTrue(elasticScroll.contains("wheel"));
        assertTrue(elasticScroll.contains("passive: false"));
        assertTrue(elasticScroll.contains("prefers-reduced-motion: reduce"));
        assertTrue(elasticScroll.contains("window.scrollY <= 0"));
        assertTrue(elasticScroll.contains("elastic-scroll-active"));
        assertTrue(elasticScroll.contains("elastic-scroll-return"));
        assertTrue(elasticScroll.contains("requestAnimationFrame"));
        assertTrue(elasticScroll.contains("var maxPull = 28;"));
        assertTrue(elasticScroll.contains("var touchResistance = 0.16;"));
        assertTrue(elasticScroll.contains("var wheelResistance = 0.075;"));
        assertTrue(elasticScroll.contains("var wheelStepLimit = 5;"));
        assertTrue(elasticScroll.contains("var releaseDuration = 180;"));
        assertTrue(elasticScroll.contains("function easeOutCubic(progress)"));
        assertTrue(elasticScroll.contains("releaseStartPull * (1 - easedProgress)"));
        assertTrue(elasticScroll.contains("Math.log1p(distance)"));
        assertFalse(elasticScroll.contains("springVelocity"));
        assertFalse(elasticScroll.contains("springStiffness"));
        assertFalse(elasticScroll.contains("springDamping"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
