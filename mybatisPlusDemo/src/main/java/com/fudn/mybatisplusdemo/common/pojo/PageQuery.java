package com.fudn.mybatisplusdemo.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页参数
 * 需要分页查询的接口，请求参数可以继承这个类
 *
 * @author fdn
 * @since 2021-09-09 18:10
 */
@Data
public class PageQuery {

    @ApiModelProperty(value = "当前页数",example = "1")
    private Integer pageNo;

    @ApiModelProperty(value = "每页数量",example = "10")
    private Integer limit;
}
