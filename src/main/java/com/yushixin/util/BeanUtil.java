package com.yushixin.util;

import com.yushixin.constant.Constant;
import com.yushixin.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Bean工具类
 */
@Slf4j
public class BeanUtil {

    /**
     * 校验Optional中对象是否为空
     * 1.为空时抛出异常
     * 2.非空时返回
     * @param <T> 泛型
     */
    @SuppressWarnings("all")
    public static <T> T validOptionalNull(final Optional<T> optional) {
        if ( !optional.isPresent() ) {
            throw new BusinessException(Constant.MsgNotFound);
        }
        return optional.get();
    }

    /**
     * Bean转Map
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if ( Objects.nonNull(obj) ) {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                log.error("Bean转Map异常", e);
            }
        }
        return map;
    }

    /**
     * 获取AOP切入点上的注解对象
     * @param jp 切入点
     * @param clazz 注解类型
     * @param <T> 泛型
     * @return T类型的注解对象
     */
    public static <T extends Annotation> T getAnnotation(final ProceedingJoinPoint jp, Class<T> clazz) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getMethod().getAnnotation(clazz);
    }

    private BeanUtil() {}
}
