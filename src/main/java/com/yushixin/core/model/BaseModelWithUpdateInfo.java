package com.yushixin.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yushixin.constant.Constant;
import com.yushixin.core.converter.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 通用-更新信息
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModelWithUpdateInfo extends BaseModelWithCreateInfo {

    private static final long serialVersionUID = 1L;

    // 更新人
    @Column(name = "UPDATE_USER", length = 32)
    @LastModifiedBy
    private String updateUser;

    // 更新时间
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "UPDATE_TIME")
    @LastModifiedDate
    @JsonFormat(pattern = Constant.TimePattern_yyyyMMddHHmmss, timezone = "GMT+8")
    private LocalDateTime updateTime;
}
