package com.yushixin.util;

import com.yushixin.constant.TestConstant;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilTest {

    @Test
    public void testAddLikeSplit() {
        assertThat(StringUtil.addLikeSplit(TestConstant.SourceString)).isEqualTo("%"+ TestConstant.SourceString +"%");
    }

    @Test
    public void testGetBytes1() {
        assertThat(StringUtil.getBytes(TestConstant.SourceString)).isEqualTo(TestConstant.SourceBytes);
    }
}
