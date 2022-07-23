package com.yushixin.suite;

import com.yushixin.route.controller.RouteControllerTest;
import com.yushixin.util.BeanUtilTest;
import com.yushixin.util.DateUtilTest;
import com.yushixin.util.StringUtilTest;
import com.yushixin.util.SystemUtilTest;
import com.yushixin.util.secret.MD5UtilTest;
import com.yushixin.util.secret.SM3UtilTest;
import com.yushixin.util.secret.SM4UtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试用例套件
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    RouteControllerTest.class,
    MD5UtilTest.class,
    SM3UtilTest.class,
    SM4UtilTest.class,
    BeanUtilTest.class,
    DateUtilTest.class,
    StringUtilTest.class,
    SystemUtilTest.class
})
public class SuiteAllTestRunner {
}
