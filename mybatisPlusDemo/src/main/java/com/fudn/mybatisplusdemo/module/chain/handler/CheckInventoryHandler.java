package com.fudn.mybatisplusdemo.module.chain.handler;

import com.fudn.mybatisplusdemo.module.chain.dto.ChainOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fdn
 * @since 2022-04-26 21:34
 */
@Component
@Slf4j
@Order(1)
public class CheckInventoryHandler extends AbstractOrderChainHandler {
    /**
     * 执行校验逻辑
     *
     * @param request
     */
    @Override
    public void execute(ChainOrderDTO request) {
        String name = request.getOrderAmount() > 100 ? "大订单" : "小订单";
        log.error("param: {}", name);
        log.error("第一个校验，校验仓库是否有库存");
    }
}
