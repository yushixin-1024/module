package com.yushixin.core.aop;

/**
 * 操作日志类型
 */
public enum OperateType {

    Register("用户注册"),

    Login("用户登录"),

    Logout("用户登出");

    // 描述信息
    private final String desc;

    OperateType(String desc) {
        this.desc = desc;
    }

    /**
     * 获取操作日志类型描述信息
     */
    public String getDesc() {
        return desc;
    }
}
