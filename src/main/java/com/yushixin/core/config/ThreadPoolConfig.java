package com.yushixin.core.config;

import com.yushixin.constant.Constant;
import com.yushixin.core.config.properties.ThreadPoolProperties;
import com.yushixin.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置类
 */
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    private final ThreadPoolProperties properties;

    public ThreadPoolConfig(ThreadPoolProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(properties.getCorePoolSize());
        // 最大线程数
        taskExecutor.setMaxPoolSize(properties.getMaxPoolSize());
        // 工作队列大小
        taskExecutor.setQueueCapacity(properties.getQueueCapacity());
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix(properties.getThreadNamePrefix());
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler((r, executor) -> {
            log.error("线程创建失败, Runnable:[{}], ThreadPoolExecutor:[{}]", r.toString(), executor.toString());
            throw new BusinessException(Constant.MsgThreadPool);
        });
        // 初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }
}
