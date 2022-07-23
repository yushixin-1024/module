package com.yushixin.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yushixin.constant.Constant;
import com.yushixin.core.converter.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 通用-创建信息
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModelWithCreateInfo extends BaseModelWithId {

    private static final long serialVersionUID = 1L;

    // 创建人
    @Column(name = "CREATE_USER", length = 32)
    @CreatedBy
    private String createUser;

    // 创建时间
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "CREATE_TIME")
    @CreatedDate
    @JsonFormat(pattern = Constant.TimePattern_yyyyMMddHHmmss, timezone = "GMT+8")
    private LocalDateTime createTime;
}
