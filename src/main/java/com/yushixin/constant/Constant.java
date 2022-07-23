package com.yushixin.constant;

/**
 * 常量类
 */
public class Constant {

    // Session中登录用户Key值
    public static final String SessionKeyUser = "User";
    // Session中用户名Key值(为了保证没有登录时,操作日志仍然可以获取到创建人/操作人)
    public static final String SessionKeyUsername = "Username";

    // 提示信息:未找到该记录
    public static final String MsgNotFound = "未找到该记录!";
    // 提示信息:操作成功
    public static final String MsgSuccess = "操作成功!";
    // 提示信息:操作失败
    public static final String MsgFail = "操作失败!";
    // 提示信息:操作失败
    public static final String MsgThreadPool = "线程创建失败!";

    // 操作代码:返回成功
    public static final int CodeSuccess = 200;
    // 操作代码:返回失败
    public static final int CodeFail = 500;

    // 时间格式:yyyyMMdd
    public static final String TimePattern_yyyyMMdd = "yyyyMMdd";
    // 时间格式:yyyy-MM-dd HH:mm:ss
    public static final String TimePattern_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    // 文件编码:GBK
    public static final String Charset_GBK = "GBK";
    // 文件编码:UTF-8
    public static final String Charset_UTF8 = "UTF-8";

    private Constant() {}
}
