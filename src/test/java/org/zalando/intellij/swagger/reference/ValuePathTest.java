package org.zalando.intellij.swagger.reference;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValuePathTest {
    private YamlValuePath yamlValuePath;
    @Before
    public void setup() {
        yamlValuePath = new YamlValuePath("gw.pl.framework.basic_auth-1.0");
    }
    @Test
    public void isRelativePath() throws Exception {

    }

    @Test
    public void hasVersion() throws Exception {
        assertTrue(yamlValuePath.hasVersion());

    }

    @Test
    public void getParentPath() throws Exception {
        assertEquals("gw.pl.framework", yamlValuePath.getParentPathOriginal());

    }

    @Test
    public void getFileNameWithoutExtension() throws Exception {
        assertEquals("basic_auth-1.0", yamlValuePath.getFileNameWithoutExtension());
    }
    
    @Test
    public void getFileName() throws Exception {
        assertEquals("basic_auth-1.0.swagger.yaml", yamlValuePath.getFileName());
    }

}