package com.yushixin.core.result;

import com.yushixin.constant.Constant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回值类
 */
@Getter
@Setter
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 状态码
    private int code;

    // 是否成功
    private boolean success;

    // 返回信息
    private String message;

    // 承载数据
    private T data;

    public R(int code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 操作成功
     */
    public static R<Void> success() {
        return new R<>(Constant.CodeSuccess, true, Constant.MsgSuccess, null);
    }

    public static <T> R<T> success(String message) {
        return new R<>(Constant.CodeSuccess, true, message, null);
    }

    public static <T> R<T> success(T data) {
        return new R<>(Constant.CodeSuccess, true, Constant.MsgSuccess, data);
    }

    public static <T> R<T> success(String message, T data) {
        return new R<>(Constant.CodeSuccess, true, message, data);
    }

    /**
     * 操作失败
     */
    public static R<Void> fail() {
        return new R<>(Constant.CodeFail, true, Constant.MsgFail, null);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(Constant.CodeFail, false, message, null);
    }

    public static <T> R<T> fail(T data) {
        return new R<>(Constant.CodeFail, false, Constant.MsgFail, data);
    }

    public static <T> R<T> fail(String message, T data) {
        return new R<>(Constant.CodeFail, false, message, data);
    }
}
