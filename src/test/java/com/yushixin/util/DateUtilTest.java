package com.yushixin.util;

import com.yushixin.constant.Constant;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTest {

    @Test
    public void testFormat() {
        // Setup
        final Temporal temporal = LocalDate.now();

        // Run the test
        final String result = DateUtil.format(temporal, Constant.TimePattern_yyyyMMdd);

        // Verify the results
        assertThat(result).isEqualTo("20220520");
    }
}
