package com.site.blog.controller.blog;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlogTemplateStaticAssetsTest {

    @Test
    public void templatesDoNotReferenceMissingBaiduScript() throws Exception {
        assertFalse(read("src/main/resources/templates/blog/amaze/header.html").contains("/blog/plugins/baidu.js"));
        assertFalse(read("src/main/resources/templates/blog/amaze/detail.html").contains("/blog/plugins/baidu.js"));
    }

    @Test
    public void headerUsesPngBackgroundWithResponsiveAspectRatio() throws Exception {
        String headerTemplate = read("src/main/resources/templates/blog/amaze/header.html");
        assertTrue(headerTemplate.contains("head-bg.png"));
        assertTrue(read("src/main/resources/templates/blog/amaze/detail.html").contains("head-bg.png"));
        assertFalse(headerTemplate.contains("head-bg2.png"));
        assertFalse(read("src/main/resources/templates/blog/amaze/detail.html").contains("head-bg2.png"));
        assertFalse(headerTemplate.contains("header-bg.png"));
        assertFalse(read("src/main/resources/templates/blog/amaze/detail.html").contains("header-bg.png"));
        assertFalse(headerTemplate.contains("header-bg3.jpg"));
        assertFalse(read("src/main/resources/templates/blog/amaze/detail.html").contains("header-bg3.jpg"));
        assertFalse(headerTemplate.contains("site-heading"));
        assertFalse(headerTemplate.contains("websiteName"));
        assertFalse(headerTemplate.contains("websiteDescription ?:"));

        BufferedImage header = ImageIO.read(Paths.get("src/main/resources/static/blog/amaze/images/head-bg.png").toFile());
        assertEquals(2508, header.getWidth());
        assertEquals(627, header.getHeight());

        String css = read("src/main/resources/static/blog/amaze/css/base.css");
        assertTrue(css.contains("body .intro-header"));
        assertTrue(css.contains("width: 100%;"));
        assertTrue(css.contains("max-width: none;"));
        assertTrue(css.contains("margin-left: 0;"));
        assertFalse(css.contains("width: 100vw;"));
        assertFalse(css.contains("max-width: 100vw;"));
        assertFalse(css.contains("margin-left: calc(50% - 50vw);"));
        assertTrue(css.contains("body .intro-header .container"));
        assertTrue(css.contains("max-width: 1170px;"));
        assertTrue(css.contains("text-align: center;"));
        assertTrue(css.contains("aspect-ratio: 4 / 1;"));
        assertTrue(css.contains("height: 25vw;"));
        assertTrue(css.contains("min-height: 260px;"));
        assertTrue(css.contains("max-height: none;"));
        assertTrue(css.contains("min-height: 220px;"));
        assertTrue(css.contains("body .intro-header::before"));
        assertTrue(css.contains("body .intro-header::after"));
        assertTrue(css.contains("animation: headerLightSweep"));
        assertTrue(css.contains("animation: headerFlow"));
        assertTrue(css.contains("@media (prefers-reduced-motion: reduce)"));
        assertTrue(css.contains("animation: none;"));
        assertTrue(css.contains("z-index: 1;"));
    }

    private String read(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
