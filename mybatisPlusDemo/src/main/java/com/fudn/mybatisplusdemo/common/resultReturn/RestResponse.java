package com.fudn.mybatisplusdemo.common.resultReturn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

/**
 * 结果返回格式，返回结果用实体包装
 *
 * @author fdn
 * @since 2021-08-24 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResponse<T> {
    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private boolean success = true;

    @JsonIgnore
    @Transient
    public boolean isOk() {
        return code == ResultCode.SUCCESS.getCode() && success;
    }

    public static <T> RestResponse<T> ok() {
        return restResult(null, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> RestResponse<T> ok(T data) {
        return restResult(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> RestResponse<T> ok(T data, String msg) {
        return restResult(data, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> RestResponse<T> failed() {
        return restResult(null, ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg(), false);
    }

    public static <T> RestResponse<T> failed(String msg) {
        return restResult(null, ResultCode.FAILURE.getCode(), msg, false);
    }

    public static <T> RestResponse<T> failed(IResultCode resultCode) {
        return restResult(null, resultCode.getCode(), resultCode.getMsg(), false);
    }

    public static <T> RestResponse<T> failed(IResultCode resultCode, String message) {
        return restResult(null, resultCode.getCode(), message, false);
    }

    public static <T> RestResponse<T> failed(T data) {
        return restResult(data, ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg(), false);
    }

    public static <T> RestResponse<T> failed(T data, String msg) {
        return restResult(data, ResultCode.FAILURE.getCode(), msg, false);
    }

    public static <T> RestResponse<T> failed(int code, String msg) {
        return restResult(null, code, msg, false);
    }

    public static <T> RestResponse<T> build(T data, int code, String msg) {
        return restResult(data, code, msg);
    }

    private static <T> RestResponse<T> restResult(T data, int code, String msg) {
        return restResult(data, code, msg, true);
    }

    /**
     * 通用错误返回
     *
     * @param resultCode
     * @return
     */
    public static <T> RestResponse<T> error(IResultCode resultCode) {
        return restResult(null, resultCode.getCode(), resultCode.getMsg(), false);
    }

    /**
     * 通用错误返回
     *
     * @param resultCode
     * @param msg
     * @return
     */
    public static <T> RestResponse<T> error(IResultCode resultCode, String msg) {
        return restResult(null, resultCode.getCode(), msg, false);
    }

    /**
     * 通用错误返回
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static <T> RestResponse<T> error(IResultCode resultCode, T data) {
        return restResult(data,resultCode.getCode(), resultCode.getMsg(), false);
    }


    private static <T> RestResponse<T> restResult(T data, int code, String msg, boolean success) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setCode(code);
        restResponse.setData(data);
        restResponse.setMsg(msg);
        restResponse.setSuccess(success);
        return restResponse;
    }
}
