package com.yushixin.core.annotation;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * 写事务
 * 1.用于类、方法上
 * 2.超时时间5分钟
 * 3.出现Exception异常回滚
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(timeout = 60 * 5, rollbackFor = Exception.class)
public @interface Write {
}
