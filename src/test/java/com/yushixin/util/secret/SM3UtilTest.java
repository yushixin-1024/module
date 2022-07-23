package com.yushixin.util.secret;

import com.yushixin.constant.TestConstant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SM3UtilTest {

    // SM3密文
    private static final String Cipher = "6e0f9e14344c5406a0cf5a3b4dfb665f87f4a771a31f7edbb5c72874a32b2957";

    // SM3摘要
    private static final byte[] Digest = new byte[] {110, 15, -98, 20, 52, 76, 84, 6, -96, -49, 90, 59, 77, -5, 102, 95, -121, -12, -89, 113, -93, 31, 126, -37, -75, -57, 40, 116, -93, 43, 41, 87};

    @Test
    public void testEncrypt() {
        assertThat(SM3Util.encrypt(TestConstant.SourceString)).isEqualTo(Cipher);
    }

    @Test
    public void testHash() {
        assertThat(SM3Util.hash(TestConstant.SourceString.getBytes())).isEqualTo(Digest);
    }
}
