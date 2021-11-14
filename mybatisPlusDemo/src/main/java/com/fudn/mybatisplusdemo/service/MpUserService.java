package com.fudn.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fudn.mybatisplusdemo.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.pojo.params.QueryUserParams;
import org.springframework.stereotype.Component;

/**
 * 继承MyBatis-Plus提供的IService接口
 * @author fdn
 * @since 2021-08-26 16:08
 */
public interface MpUserService extends IService<MpUserPojo> {

    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    Page<MpUserPojo> queryUserList(QueryUserParams params);
}
