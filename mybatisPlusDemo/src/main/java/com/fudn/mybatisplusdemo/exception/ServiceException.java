package com.fudn.mybatisplusdemo.exception;

import com.fudn.mybatisplusdemo.common.resultReturn.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fdn
 * @since 2022-04-17 21:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8586915463358667791L;

    private IResultCode resultCode;

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

}
