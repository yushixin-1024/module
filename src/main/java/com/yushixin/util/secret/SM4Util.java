package com.yushixin.util.secret;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Objects;

/**
 * SM4工具类
 */
@Slf4j
public class SM4Util {

    // 自动生成的随机密钥
    private static final String SM4_KEY = "eec73b6065a5a77929f30c5b8562b624";
    private static final String ALGORITHM_NAME = "SM4";
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";

    static {
        if ( Objects.isNull(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) ) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 加密(字节数组)
     * @param data 要加密的明文字节数组
     * @return 加密后的密文字节数组
     */
    public static byte[] encrypt(byte[] data) {
        return sm4(data, ByteUtils.fromHexString(SM4_KEY), Cipher.ENCRYPT_MODE);
    }

    /**
     * 加密(字符串)
     * @param data 要加密的明文字符串
     * @return 加密后的密文字符串
     */
    public static String encrypt(String data) {
        return ByteUtils.toHexString( sm4(data.getBytes(), ByteUtils.fromHexString(SM4_KEY), Cipher.ENCRYPT_MODE) );
    }

    /**
     * 解密(字节数组)
     * @param data 要解密的密文字节数组
     * @return 解密后的明文字节数组
     */
    public static byte[] decrypt(byte[] data) {
        return sm4(data, ByteUtils.fromHexString(SM4_KEY), Cipher.DECRYPT_MODE);
    }

    /**
     * 解密(字符串)
     * @param data 要解密的密文字符串
     * @return 解密后的明文字符串
     */
    public static String decrypt(String data) {
        return new String( sm4(ByteUtils.fromHexString(data), ByteUtils.fromHexString(SM4_KEY), Cipher.DECRYPT_MODE) );
    }

    /**
     * SM4对称加解密
     * @param input 明文(加密模式) 或 密文(解密模式)
     * @param key   密钥
     * @param mode  Cipher.ENCRYPT_MODE(加密) 或 Cipher.DECRYPT_MODE(解密)
     * @return 密文（加密模式）或明文（解密模式）
     */
    @SneakyThrows(Exception.class)
    private static byte[] sm4(byte[] input, byte[] key, int mode) {
        SecretKeySpec sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB_PKCS5PADDING, BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(mode, sm4Key);
        return cipher.doFinal(input);
    }

    /**
     * 生成随机密钥
     * @param bits 生成密钥位数
     * @return 返回32位(128/4)十六进制字符串
     */
    public static String generateKey(int bits) {
        KeyGenerator kg;
        // 出现异常时的默认值
        String key = SM4_KEY;
        try {
            kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
            kg.init(bits, new SecureRandom());
            key = ByteUtils.toHexString(kg.generateKey().getEncoded());
        } catch (Exception e) {
            log.warn("生成SM4密钥异常,SM4Util#generateKey(128)", e);
        }
        return key;
    }

    private SM4Util() {}
}
