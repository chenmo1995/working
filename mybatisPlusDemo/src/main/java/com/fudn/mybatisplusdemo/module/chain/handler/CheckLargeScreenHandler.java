package com.fudn.mybatisplusdemo.module.chain.handler;

import com.fudn.mybatisplusdemo.module.chain.dto.ChainOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fdn
 * @since 2022-04-26 21:41
 */
@Component
@Order(3)
@Slf4j
public class CheckLargeScreenHandler extends AbstractOrderChainHandler{
    /**
     * 执行校验逻辑
     *
     * @param request
     */
    @Override
    public void execute(ChainOrderDTO request) {
        Integer name = request.getOrderStatus();
        log.error("param: {}", name);
        log.error("第三个校验，校验是否是大屏产品");
    }
}
