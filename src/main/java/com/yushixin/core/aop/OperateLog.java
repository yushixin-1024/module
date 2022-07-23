package com.yushixin.core.aop;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * 1.声明在方法上,记录该方法的操作信息
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OperateLog {

    /**
     * 操作日志类型
     */
    OperateType value();
}
