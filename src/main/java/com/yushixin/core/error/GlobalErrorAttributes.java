package com.yushixin.core.error;

import com.yushixin.core.result.R;
import com.yushixin.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 全局异常属性
 */
@Slf4j
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        String uri = this.getAttr(webRequest, "javax.servlet.error.request_uri");
        Integer status = this.getAttr(webRequest, "javax.servlet.error.status_code");
        Throwable error = getError(webRequest);
        R<Object> result;
        if ( error == null ) {
            log.error("请求路径:{},错误状态:{}", uri, status);
            result = R.fail("系统未知异常[HttpStatus]:" + status);
        } else {
            log.error(String.format("请求路径:%s,错误状态:%d", uri, status), error);
            result = R.fail(error.getMessage());
        }
        return BeanUtil.beanToMap(result);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private <T> T getAttr(WebRequest webRequest, String name) {
        return (T) webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
