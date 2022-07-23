package com.yushixin.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemUtilTest {

    @Test
    public void testGetOSName() {
        assertThat(SystemUtil.getOSName()).isEqualTo("Windows 10");
    }

    @Test
    public void testGetCommandEncoding() {
        assertThat(SystemUtil.getCommandEncoding()).isEqualTo("GBK");
    }

    @Test
    public void testGet7zPrefix() {
        assertThat(SystemUtil.get7zPrefix()).isEqualTo("7z");
    }
}
