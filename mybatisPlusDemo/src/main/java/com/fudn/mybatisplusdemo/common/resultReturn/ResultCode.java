package com.fudn.mybatisplusdemo.common.resultReturn;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 返回接口的实现类，只是一个模板，实际使用可以自己编写
 *
 * @author fdn
 * @since 2021-08-24 17:01
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {
    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 失败
     */
    FAILURE(-1, "fail"),

    /**
     * 没有获取到当前用户
     */
    USER_NOT_FOUND(401, "Current User Not Found"),

//    /**
//     * 系统异常
//     */
//    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
//
//    /**
//     * 未认证
//     */
//    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),
//
//    /**
//     * 404
//     */
//    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),
//
//    /**
//     * 不支持的Method
//     */
//    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),
//
//    /**
//     * MediaType不支持
//     */
//    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported"),
//
//    /**
//     * 禁止访问
//     */
//    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "403 Forbidden"),
//
//    /**
//     * 参数校验失败
//     */
//    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error"),

    /**
     * fall-back
     */
    FAIL_FALL_BACK(9998, "fall back");

    final int code;

    final String msg;

    /**
     * 根据code获取枚举对象
     *
     * @param code
     * @return
     */
    public static ResultCode fromCode(long code) {
        ResultCode[] ecs = ResultCode.values();
        for (ResultCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        throw new IllegalArgumentException("Invalid Enum code:" + code);
    }

    /**
     * 根据code获取枚举对象2
     *
     * @param code
     * @return
     */
    public static ResultCode getEnumByCode(long code) {
        Objects.requireNonNull(code);
        return Stream.of(values()).
                filter(item -> item.getCode() == (code)).
                findAny().
                orElseThrow(() -> new IllegalArgumentException("Invalid Enum code:" + code));
    }

}
