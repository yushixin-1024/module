package com.yushixin.util.secret;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
 * MD5工具类
 */
public class MD5Util {

    /**
     * 生成MD5摘要[32位十六进制,全大写]
     * @param bytes 字节数组
     * @return MD5值
     */
    public static String md5(byte[] bytes) {
        MD5Digest digest = new MD5Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] md5 = new byte[digest.getDigestSize()];
        digest.doFinal(md5, 0);
        return ByteUtils.toHexString(md5).toUpperCase();
    }

    private MD5Util() {}
}
