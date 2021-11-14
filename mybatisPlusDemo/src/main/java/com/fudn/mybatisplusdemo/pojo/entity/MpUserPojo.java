package com.fudn.mybatisplusdemo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author fdn
 * @since 2021-08-26 16:00
 */
@TableName("mp_user")
@Data
@Accessors(chain = true)
public class MpUserPojo {
    /**
     * MyBatis-Plus默认名为'id'的字段是主键
     * 如果主键名不叫'id'，而是'userId'之类的，必须通过 @TableId 标识
     * 主键生成策略默认是无意义的long数值，可以指定@TableId的IdType属性为AUTO，随数据库自增
     * 加上 @TableId(type = IdType.AUTO)
     */
    @TableField("id")
    @ApiModelProperty(value = "主键id",example = "0")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄",example = "0")
    private Integer age;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型",example = "0")
    private Integer userType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 是否删除，逻辑删除请用 @TableLogic
     */
    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

    /**
     * 乐观锁版本号，需要乐观锁请用 @Version
     * 支持的字段类型:
     * long,
     * Long,
     * int,
     * Integer,
     * java.util.Date,
     * java.sql.Timestamp,
     * java.time.LocalDateTime
     */
    @Version
    @ApiModelProperty(value = "版本号",example = "0")
    private Integer version;
}
