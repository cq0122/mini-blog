package com.site.blog.build;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PomDependencyCleanupTest {

    @Test
    public void unusedRuntimeDependenciesAreNotDeclared() throws Exception {
        String pom = new String(Files.readAllBytes(Paths.get("pom.xml")), StandardCharsets.UTF_8);

        assertFalse(pom.contains("<artifactId>spring-session-core</artifactId>"));
        assertFalse(pom.contains("<artifactId>mybatis-plus-generator</artifactId>"));
        assertFalse(pom.contains("<artifactId>velocity-engine-core</artifactId>"));
    }

    @Test
    public void codeGeneratorToolIsKeptOutsidePackagedSources() {
        assertFalse(Files.exists(Paths.get("src/main/java/com/site/blog/util/CodeGenerator.java")));
        assertTrue(Files.exists(Paths.get("static-files/original-assets/CodeGenerator.java")));
    }
}
