package com.fudn.mybatisplusdemo.exception;

import com.fudn.mybatisplusdemo.common.resultReturn.RestResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 重写默认的所有统一异常
 * 统一错误信息处理
 * @Author shengyi.feng
 * @return
 **/
@RestController
public class GlobalErrorHandler implements ErrorController
{
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public RestResponse<Object> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return RestResponse.build(Integer.valueOf(status.value()), status.getReasonPhrase());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}