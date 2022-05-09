package com.fudn.mybatisplusdemo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fudn.mybatisplusdemo.aop.annotation.ApiLog;
import com.fudn.mybatisplusdemo.common.resultReturn.RestResponse;
import com.fudn.mybatisplusdemo.common.resultReturn.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author fdn
 * @since 2022-04-17 00:08
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionTranslator {

    @ApiLog
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse handleError(MethodArgumentNotValidException e) {
        log.error("Method Argument Not Valid:{}", e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return RestResponse.failed(ResultCode.PARAM_VALID_ERROR, msg);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse handleError(ConstraintViolationException e) {
        log.error("Constraint Violation:{}", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());

        return RestResponse.failed(ResultCode.PARAM_VALID_ERROR, message);
    }

    @ExceptionHandler(ServiceException.class)
    public RestResponse handleError(ServiceException e) {
        log.info("ServiceException");
        return RestResponse.failed(e.getResultCode());
    }

    @ExceptionHandler(Throwable.class)
    public RestResponse handleError(Throwable e) {
        log.error("Internal Server Error:{}", e);

        return RestResponse.failed(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 其他未处理的异常
    @ExceptionHandler(Exception.class)
    public RestResponse exceptionHandler(Exception e) {
        log.error("Exception Internal Server Error:{}", e);
        log.info("Exception");

        return RestResponse.failed(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public RestResponse handleError(MissingServletRequestParameterException e) throws JsonProcessingException {
//        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
//
//        RestResponse result = RestResponse.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                message);
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                MethodReleaseUtils.getInvokeElapsed(),
//                objectMapper.writeValueAsString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public RestResponse handleError(MethodArgumentTypeMismatchException e) {
//        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
//        RestResponse result = RestResponse.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                message);
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                MethodReleaseUtils.getInvokeElapsed(),
//                StringUtils.substring(JSON.toJSONString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }


//    private RestResponse getRestResponse(BindingResult bindingResult) {
//        FieldError error = bindingResult.getFieldError();
//        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
//
//        RestResponse result = RestResponse.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                message);
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                MethodReleaseUtils.getInvokeElapsed(),
//                StringUtils.substring(JSON.toJSONString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }
//
//    @ExceptionHandler(BindException.class)
//    public RestResponse handleError(BindException e) {
//        log.error("Bind Exception:{}", e);
//        return getRestResponse(e);
//    }


//    @ExceptionHandler(NoHandlerFoundException.class)
//    public RestResponse handleError(NoHandlerFoundException e) {
//        RestResponse result = RestResponse.failed(ResultCode.NOT_FOUND);
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                MethodReleaseUtils.getInvokeElapsed(),
//                StringUtils.substring(JSON.toJSONString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public RestResponse handleError(HttpMessageNotReadableException e) {
//
//        RestResponse result = RestResponse.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                "Message Not Readable");
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                MethodReleaseUtils.getInvokeElapsed(),
//                StringUtils.substring(JSON.toJSONString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public RestResponse handleError(HttpRequestMethodNotSupportedException e) {
//        RestResponse result = RestResponse.failed(ResultCode.METHOD_NOT_SUPPORTED);
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                System.currentTimeMillis() - MethodReleaseUtils.getInvokeStart(),
//                StringUtils.substring(JSON.toJSONString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public RestResponse handleError(HttpMediaTypeNotSupportedException e) {
//        RestResponse result = RestResponse.failed(ResultCode.MEDIA_TYPE_NOT_SUPPORTED);
//
//        log.info("[{}] - 耗时：{}，请求应答：{}",
//                MethodReleaseUtils.getInvokeToken(),
//                System.currentTimeMillis() - MethodReleaseUtils.getInvokeStart(),
//                StringUtils.substring(JSON.toJSONString(result), 0, CommonConstants.RES_LENGTH));
//        return result;
//    }
}
