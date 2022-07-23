package com.yushixin.core.config;

import com.yushixin.constant.Constant;
import com.yushixin.util.RequestUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * 自动填充配置类
 */
@Configuration
@EnableJpaAuditing
public class AutoFillAuditor implements AuditorAware<String>, DateTimeProvider {

    /**
     * 获取当前登录用户名
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        String username = RequestUtil.readCurrentSession(Constant.SessionKeyUsername);
        return Optional.of(username);
    }

    /**
     * 获取当前时间
     */
    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now());
    }
}
