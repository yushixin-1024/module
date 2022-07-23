package com.yushixin.core.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 通用-ID信息
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModelWithId implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID", length = 32)
    private String id;
}
