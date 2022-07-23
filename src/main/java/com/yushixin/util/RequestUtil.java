package com.yushixin.util;

import com.yushixin.constant.Constant;
import com.yushixin.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 请求工具类
 */
@Slf4j
public class RequestUtil {

    /**
     * 获取当前请求对象
     * @return HttpServletRequest
     */
    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    /**
     * 获取当前会话对象
     * @return HttpSession
     */
    public static HttpSession getCurrentSession() {
        return getCurrentRequest().getSession();
    }

    /**
     * 向当前会话对象中写入值
     */
    public static void writeCurrentSession(String key, Object value) {
        getCurrentSession().setAttribute(key, value);
    }

    /**
     * 从当前会话对象中读取值
     * 1.返回值类型匹配错误会抛出类型转换异常
     * @see ClassCastException 类型转换异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T readCurrentSession(String key) {
        return (T) getCurrentSession().getAttribute(key);
    }

    /**
     * 文件下载设置响应
     * @param filePath 文件绝对路径
     */
    public static void downloadResponse(String filePath, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 创建文件对象
            File file = new File(filePath);
            // 文件名
            String fileName = file.getName();
            // 设置字符编码
            response.setCharacterEncoding(Constant.Charset_UTF8);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            // 设置下载的Content-Disposition
            setDisposition(fileName, response);
            // 设置下载ContentType
            setContentType(fileName, response);
            clearCache(request, response);
            byte[] bytes = FileCopyUtils.copyToByteArray(file);
            FileCopyUtils.copy(bytes, response.getOutputStream());
            // 删除本地压缩文件
            if ( !file.delete() ) {
                log.warn("本地压缩文件删除失败,压缩文件路径:[{}]", filePath);
            }
        } catch (IOException e) {
            log.error("文件下载设置响应失败", e);
            throw new BusinessException("文件下载设置响应失败");
        }
    }

    /**
     * 设置下载的Content-Disposition
     * @param fileName 下载文件名
     */
    public static void setDisposition(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 浏览器自动解码
        String $fileName = URLEncoder.encode(fileName, Constant.Charset_UTF8);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + $fileName + "\"");
    }

    /**
     * 设置下载ContentType
     * @param fileName 下载文件名
     */
    public static void setContentType(String fileName, HttpServletResponse response) {
        String contentType = "application/octet-stream";
        if ( StringUtils.hasText(fileName) ) {
            String $fileName = fileName.trim().toLowerCase();
            if ( $fileName.endsWith(".xls") ) {
                contentType = "application/vnd.ms-excel";
            } else if ( $fileName.endsWith(".xlsx") ) {
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            } else if ( $fileName.endsWith(".doc") ) {
                contentType = "application/msword";
            } else if ( $fileName.endsWith(".docx") ) {
                contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            } else if ( $fileName.endsWith(".pdf") ) {
                contentType = "application/pdf";
            } else if ( $fileName.endsWith(".jpg") || $fileName.endsWith(".jpeg") ) {
                contentType = "image/jpeg";
            } else if ( $fileName.endsWith(".png") ) {
                contentType = "image/png";
            } else if ( $fileName.endsWith(".gif") ) {
                contentType = "image/gif";
            } else if ( $fileName.endsWith(".zip") ) {
                contentType = "application/zip";
            } else if ( $fileName.endsWith(".7z") ) {
                contentType = "application/x-7z-compressed";
            } else if ( $fileName.endsWith(".rar") ) {
                contentType = "application/x-rar-compressed";
            } else if ( $fileName.endsWith(".gz") ) {
                contentType = "application/x-gzip";
            } else if ( $fileName.endsWith("tar") ) {
                contentType = "application/x-tar";
            }
        }
        response.setContentType(contentType + ";charset=UTF-8");
    }

    /**
     * 根据请求头中的UserAgent字段判断用户是否通过IE浏览器访问
     * @return boolean
     */
    public static boolean isMSIE(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        boolean isIE = false;
        if ( !StringUtils.hasText(userAgent) ) {
            return false;
        }
        userAgent = userAgent.toUpperCase();
        if ( userAgent.contains("MSIE") || userAgent.contains("TRIDENT") || userAgent.contains("EDGE") ) {
            isIE = true;
        }
        return isIE;
    }

    /**
     * 清空返回缓存
     */
    public static void clearCache(HttpServletRequest request, HttpServletResponse response) {
        // 预先判断客户端是否使用IE浏览器
        if ( isMSIE(request) ) {
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0, max-age=30");
            response.setHeader("Pragma", "public");
        } else {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        }
    }

    /**
     * 获取当前请求的实际IP地址
     * 1.对于设置了WAF应用防火墙的客户端取真实IP地址,优先取Waf-ClientIp
     * 2.Waf-ClientIp为空时取X-Forwarded-For
     * 3.X-Forwarded-For为空时取X-Real-IP
     * ...
     * n.最后仍获取不到时,默认取"unknown"
     * @return 真实IP地址
     */
    public static String getRemoteIp() {
        // 当前请求对象
        HttpServletRequest request = getCurrentRequest();
        // 对于设置了WAF应用防火墙的客户端取真实IP地址
        String ip = request.getHeader("x-Real-IP");
        log.trace("当前IP来源[X-Real-IP], 值[{}]", ip);
        if( !validEmptyIp(ip) ) {
            return ip;
        }
        if ( validEmptyIp(ip) ) {
            ip = request.getHeader("X-Forwarded-For");
            log.trace("当前IP来源[X-Forwarded-For], 值[{}]", ip);
            if( !validEmptyIp(ip) ) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                int index = ip.indexOf(',');
                if ( index != -1 ) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
        } else {
            return ip;
        }
        if ( validEmptyIp(ip) ) {
            ip = request.getHeader("Proxy-Client-IP");
            log.trace("当前IP来源[Proxy-Client-IP], 值[{}]", ip);
        }
        if ( validEmptyIp(ip) ) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.trace("当前IP来源[WL-Proxy-Client-IP], 值[{}]", ip);
        }
        if ( validEmptyIp(ip) ) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.trace("当前IP来源[HTTP_CLIENT_IP], 值[{}]", ip);
        }
        if ( validEmptyIp(ip) ) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.trace("当前IP来源[HTTP_X_FORWARDED_FOR], 值[{}]", ip);
        }
        if ( validEmptyIp(ip) ) {
            ip = request.getRemoteAddr();
            log.trace("当前IP来源[getRemoteAddr], 值[{}]", ip);
        }
        if ( validEmptyIp(ip) ) {
            return "unknown";
        }
        return ip;
    }

    /**
     * 校验IP地址是否为空
     * @param ip IP地址
     * @return 是否合法
     */
    private static boolean validEmptyIp(String ip) {
        return !StringUtils.hasText(ip);
    }

    private RequestUtil() {}
}
