package com.yushixin.util.secret;

import com.yushixin.constant.TestConstant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MD5UtilTest {

    // MD5密文
    private static final String Cipher = "202CB962AC59075B964B07152D234B70";

    @Test
    public void testMd5() {
        assertThat(MD5Util.md5(TestConstant.SourceString.getBytes())).isEqualTo(Cipher);
    }
}
