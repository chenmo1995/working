package com.fudn.mybatisplusdemo.module.pojo.dto;

import com.fudn.mybatisplusdemo.constraint.MyConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 添加用户的方法参数
 *
 * @author fdn
 * @since 2021-11-10 16:19
 */
@ApiModel(value = "添加用户的方法参数", description = "添加用户的参数实体")
@Builder(toBuilder = true)
@Data
public class AdduserDTO {

    @ApiModelProperty(value = "姓名", required = true)
    @NotEmpty(message = "诶诶诶，姓名可不能为空啊")
    @NotBlank(message = "淦，姓名空了")
    private String name;

    @ApiModelProperty(value = "年龄", example = "0", required = true)
    @DecimalMax(value = "18",message = "年龄错了")
    private Integer age;

    @MyConstraint(message = "值必须是5")
    @ApiModelProperty(value = "用户类型", example = "0")
    private Integer userType;

}
