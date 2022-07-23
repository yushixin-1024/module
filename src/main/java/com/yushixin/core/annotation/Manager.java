package com.yushixin.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 管理注解
 * Repository -> Manager -> Service -> Controller
 * 1.负责管理多个Repository层的复杂使用,减少事务嵌套
 * 2.仅用于类上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
public @interface Manager {
}
