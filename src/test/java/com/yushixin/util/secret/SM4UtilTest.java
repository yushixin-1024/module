package com.yushixin.util.secret;

import com.yushixin.constant.TestConstant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SM4UtilTest {

    // SM4密文
    private static final String CipherString = "8d24440f28d8acab3097546d6ff4fabd";

    // SM3摘要
    private static final byte[] CipherBytes = new byte[] {-115, 36, 68, 15, 40, -40, -84, -85, 48, -105, 84, 109, 111, -12, -6, -67};

    @Test
    public void testEncrypt1() {
        assertThat(SM4Util.encrypt(TestConstant.SourceBytes)).isEqualTo(CipherBytes);
    }

    @Test
    public void testEncrypt2() {
        assertThat(SM4Util.encrypt(TestConstant.SourceString)).isEqualTo(CipherString);
    }

    @Test
    public void testDecrypt1() {
        assertThat(SM4Util.decrypt(CipherBytes)).isEqualTo(TestConstant.SourceBytes);
    }

    @Test
    public void testDecrypt2() {
        assertThat(SM4Util.decrypt(CipherString)).isEqualTo(TestConstant.SourceString);
    }

    @Test
    public void testGenerateKey() {
        assertThat(SM4Util.generateKey(128)).hasSize(32);
    }
}
