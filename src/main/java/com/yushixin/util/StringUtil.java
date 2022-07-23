package com.yushixin.util;

import com.yushixin.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * 字符串工具类
 */
@Slf4j
public class StringUtil {

    /**
     * 拼接Like SQL的'%'
     */
    public static String addLikeSplit(String value) {
        return "%" + value + "%";
    }

    /**
     * 字符串转字节数组
     * @param value 字符串
     * @return byte[] 字节数组
     */
    public static byte[] getBytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * File文件转字节数组
     * @param file 文件
     * @return byte[] 字节数组
     */
    public static byte[] getBytes(File file) {
        try {
            return Files.readAllBytes( file.toPath() );
        } catch (IOException e) {
            log.error("文件转字节数组异常", e);
            throw new BusinessException("文件转字节数组失败");
        }
    }

    /**
     * MultipartFile文件转字节数组
     * @param file 文件
     * @return byte[] 字节数组
     */
    public static byte[] getBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            log.error("文件转字节数组异常", e);
            throw new BusinessException("文件转字节数组失败");
        }
    }

    private StringUtil() {}
}
