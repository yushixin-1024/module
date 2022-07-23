package com.yushixin.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * JPA分页参数工具类
 */
public class PageRequestUtil {

    /**
     * 生成PageRequest对象
     * 1.按创建时间降序排列
     * @param pageable 分页参数
     */
    public static PageRequest create(Pageable pageable) {
        return PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(Sort.Direction.DESC, "createTime")
        );
    }

    private PageRequestUtil() {}
}
