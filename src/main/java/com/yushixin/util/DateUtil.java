package com.yushixin.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * Temporal格式化为指定格式
     * @see LocalDate
     * @see LocalDateTime
     */
    public static String format(Temporal temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    private DateUtil() {}
}
