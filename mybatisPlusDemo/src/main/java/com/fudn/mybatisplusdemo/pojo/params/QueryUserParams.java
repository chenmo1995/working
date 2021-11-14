package com.fudn.mybatisplusdemo.pojo.params;

import com.fudn.mybatisplusdemo.common.pojo.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fdn
 * @since 2021-09-09 18:09
 */
@Data
public class QueryUserParams extends PageQuery {

    @ApiModelProperty(value = "人员id", name = "abc")
    private String id;

    @ApiModelProperty(value = "姓名", notes = "可以模糊查询")
    private String name;

    @ApiModelProperty(value = "人员类型", example = "0", notes = "可选[1,2,3],分别代表[a,b,c]")
    private int userType = 0;
}
