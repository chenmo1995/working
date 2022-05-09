package com.fudn.mybatisplusdemo.module.chain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fdn
 * @since 2022-04-27 01:01
 */
@Data
public class ChainOrderDTO {
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
    @ApiModelProperty(value = "订单金额")
    private Double orderAmount;
    @ApiModelProperty(value = "订单类型")
    private Integer orderType;
    @ApiModelProperty(value = "订单描述")
    private String orderDesc;
}
