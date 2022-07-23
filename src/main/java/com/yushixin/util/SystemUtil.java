package com.yushixin.util;

import com.yushixin.constant.Constant;

/**
 * 系统信息工具类
 */
public class SystemUtil {

    /**
     * 获取当前操作系统名称
     */
    public static String getOSName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取当前操作系统命令行编码
     */
    public static String getCommandEncoding() {
        String osName = getOSName();
        // Windows操作系统命令行使用GBK编码
        if ( osName.contains("Windows") ) {
            return Constant.Charset_GBK;
        }
        // 其他操作系统使用UTF-8编码
        return Constant.Charset_UTF8;
    }


    /**
     * 获取7z命令行前缀
     */
    public static String get7zPrefix() {
        String osName = SystemUtil.getOSName();
        if ( osName.contains("Windows") ) {
            return "7z";
        }
        return "7za";
    }

    private SystemUtil() {}
}
