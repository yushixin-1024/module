package com.yushixin.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * LocalDateTime转换器
 * 1.由于JPA规范在Java8之前发布,所以JPA不能处理新的Date and Time API,需要手动转换
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    /**
     * 类字段转换为数据库列
     * LocalDateTime -> Timestamp
     */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime).map(Timestamp::valueOf).orElse(null);
    }

    /**
     * 数据库列转换为类字段
     * Timestamp -> LocalDateTime
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).orElse(null);
    }
}
