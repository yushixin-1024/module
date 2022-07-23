package com.yushixin.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池属性类
 */
@Data
@Component
@ConfigurationProperties(prefix = "thread.pool", ignoreUnknownFields = false)
public class ThreadPoolProperties {

    // 核心线程数
    private Integer corePoolSize;

    // 最大线程数
    private Integer maxPoolSize;

    // 工作队列大小
    private Integer queueCapacity;

    // 线程名称前缀
    private String threadNamePrefix;
}
