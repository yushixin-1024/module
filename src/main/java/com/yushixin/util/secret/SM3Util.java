package com.yushixin.util.secret;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Objects;

/**
 * SM3工具类
 */
public class SM3Util {

    static {
        if ( Objects.isNull(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) ){
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 加密
     * @param value 待加密字符串
     * @return 返回64位(256/4)十六进制字符串
     */
    public static String encrypt(String value) {
        byte[] src = value.getBytes(StandardCharsets.UTF_8);
        byte[] srcHash = hash(src);
        return ByteUtils.toHexString(srcHash);
    }

    /**
     * 生成对应的hash值
     * @return 返回长度为32的byte数组
     */
    public static byte[] hash(byte[] src) {
        SM3Digest digest = new SM3Digest();
        digest.update(src, 0, src.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    private SM3Util() {}
}
