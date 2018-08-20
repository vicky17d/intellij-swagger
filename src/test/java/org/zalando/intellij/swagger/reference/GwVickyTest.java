package org.zalando.intellij.swagger.reference;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GwVickyTest {
    GwVicky gwVicky;

    @Before
    public void setup() {
        gwVicky = new GwVicky();
    }
    
    //lets say base path of the containging file is config/integration/apis/gw.pl.framework.dev_template-1.0

    @Test
    public void resolveGwFileReferenceBetter() throws Exception {
        assertNull(gwVicky.resolveGwFileReference(null, "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));

    }

    @Test
    public void testSimpleName() {
        assertEquals("hello.swagger.yaml", gwVicky.resolveGwFileReference("hello", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));

    }

    @Test
    public void testSimpleNameWithHyphen() {
        assertEquals("hello-1.0.swagger.yaml", gwVicky.resolveGwFileReference("hello-1.0", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));
    }
    @Test
    public void testSimpleNameWithHyphenNoVersion() {
        assertEquals("hello-hi.swagger.yaml", gwVicky.resolveGwFileReference("hello-hi", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));
    }

    @Test
    public void testSimpleNameDoubleHyphen() {
        assertEquals("hello-hi-1.0.swagger.yaml", gwVicky.resolveGwFileReference("hello-hi-1.0", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/dev_template-1.0.swagger.yaml"));
    }

    @Test
    public void testSimpleNameDoubleHyphen2() {
        assertEquals("hello-hi-1.0.swagger.yaml", gwVicky.resolveGwFileReference("gw.hello-hi-1.0", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/dev_template-1.0.swagger.yaml"));
    }

    @Test
    public void testSimpleNameWithHyphenDiffDirectory() {
        assertEquals("../../hello-hi.swagger.yaml", gwVicky.resolveGwFileReference("gw.hello-hi", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));
    }

    @Test
    public void testSimpleNameWithHyphenDiffDirectory2() {
        assertEquals("../hello-hi.swagger.yaml", gwVicky.resolveGwFileReference("gw.pl.hello-hi", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));
    }

    @Test
    public void testSimpleNameWithUpPath() {
        assertEquals("../hello-hi.swagger.yaml", gwVicky.resolveGwFileReference("gw.pl.hello-hi", "/Users/vdahuja/studio/acceptance/gmaster/pl/platform/pl/config/integration/apis/gw/pl/framework/dev_template-1.0.swagger.yaml"));
    }
}