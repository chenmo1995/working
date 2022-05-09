package com.fudn.mybatisplusdemo.module.chain.handler;

import com.fudn.mybatisplusdemo.module.chain.dto.ChainOrderDTO;

/**
 * @author fdn
 * @since 2022-04-26 21:27
 */
public abstract class AbstractChainHandler {

    // 下一个处理者
    private AbstractChainHandler nextHandler;

    public void setNextHandler(AbstractChainHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public AbstractChainHandler getNextHandler() {
        return nextHandler;
    }

    public void filter(ChainOrderDTO request) {
        execute(request);
        if (this.getNextHandler() != null) {
            this.getNextHandler().filter(request);
        }
    }

    /**
     * 执行校验逻辑
     * @param request
     */
    public abstract void execute(ChainOrderDTO request);

}
