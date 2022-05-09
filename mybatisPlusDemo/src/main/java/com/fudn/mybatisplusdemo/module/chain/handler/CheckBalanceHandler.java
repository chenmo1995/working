package com.fudn.mybatisplusdemo.module.chain.handler;

import com.fudn.mybatisplusdemo.module.chain.dto.ChainOrderDTO;
import com.fudn.mybatisplusdemo.module.chain.handler.AbstractOrderChainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fdn
 * @since 2022-04-26 21:39
 */
@Component
@Slf4j
@Order(2)
public class CheckBalanceHandler extends AbstractOrderChainHandler {
    /**
     * 执行校验逻辑
     *
     * @param request
     */
    @Override
    public void execute(ChainOrderDTO request) {
        log.error("param: {}", request.getOrderId());
        log.error("第二个校验，校验客户余额");
    }
}
