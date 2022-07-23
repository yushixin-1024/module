package com.yushixin.util;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanUtilTest {

    @Test
    public void testValidOptionalNull() {
        // Setup
        final Object obj = new Object();
        final Optional<Object> optional = Optional.of(obj);

        // Run the test
        final Object result = BeanUtil.validOptionalNull(optional);

        // Verify the results
        assertThat(result).isEqualTo(obj);
    }

    @Test
    public void testBeanToMap() {
        // Setup
        // Run the test
        // Verify the results
    }
}
