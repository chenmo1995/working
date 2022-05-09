package com.fudn.mybatisplusdemo.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import org.springframework.stereotype.Repository;


/**
 * 继承MyBatis-Plus提供的BaseMapper，提供了增删改查及分页方法，基本已经完全满足日常开发需求
 * @author fdn
 * @since 2021-08-26 16:04
 */
@Repository
public interface MpUserMapper extends BaseMapper<MpUserPojo> {
}
