package com.fudn.mybatisplusdemo.common.resultReturn;

/**
 * 返回结果接口
 *
 * @author fdn
 * @since 2021-08-24 16:59
 */
public interface IResultCode {
    /**
     * 编码
     *
     * @return
     */
    int getCode();

    /**
     * 描述
     *
     * @return
     */
    String getMsg();
}
