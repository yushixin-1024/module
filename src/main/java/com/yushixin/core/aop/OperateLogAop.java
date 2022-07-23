package com.yushixin.core.aop;

import com.yushixin.core.exception.BusinessException;
import com.yushixin.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 操作日志AOP
 */
@Slf4j
@Aspect
@Component
public class OperateLogAop {

    // 切入点
    @Pointcut("@annotation(OperateLog)")
    public void pointcut() {}

    // 环绕通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint jp) {
        log.debug("操作日志AOP开始");
        Object proceed;
        boolean result = true;
        OperateLog operateLog = BeanUtil.getAnnotation(jp, OperateLog.class);
        try {
            proceed = jp.proceed();
        } catch (Throwable t) {
            // 操作结果置为失败
            result = false;
            throw new BusinessException(t.getMessage(), t);
        } finally {
            // 捕获异常,不影响业务逻辑
            try {
                // 新增操作日志
                log.info("新增操作日志:操作类型:[{}], 结果:[{}]", operateLog.value().getDesc(), result);
            } catch (Exception e) {
                log.error("新增操作日志", e);
            }
            log.debug("操作日志AOP结束");
        }
        return proceed;
    }
}
