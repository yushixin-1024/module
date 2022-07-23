package com.yushixin.constant;

import org.springframework.mock.web.MockHttpSession;

import java.util.UUID;

/**
 * 单元测试常量类
 */
public class TestConstant {

    // 原文字符串
    public static final String SourceString = "123";

    // 原文字节数组
    public static final byte[] SourceBytes = SourceString.getBytes();

    // 随机字符串(32位)
    public static final String RandomString;

    // 用户Session
    public static final MockHttpSession Session = new MockHttpSession();

    static {
        // 随机字符串
        RandomString = UUID.randomUUID().toString().replace("-", "");
    }

    private TestConstant() {}
}
