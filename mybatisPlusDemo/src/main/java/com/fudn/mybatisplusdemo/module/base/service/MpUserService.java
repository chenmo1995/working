package com.fudn.mybatisplusdemo.module.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.base.pojo.params.QueryUserParams;

/**
 * @author fdn
 * @since 2022-02-17 15:06
 */
public interface MpUserService extends IService<MpUserPojo> {
    /**
     * 分页查询用户列表
     *
     * @param params
     * @return
     */
    Page<MpUserPojo> queryUserList(QueryUserParams params);

    /**
     * 更新 mpUserPojo
     *
     * @param mpUserPojo
     */
    void changeUser(MpUserPojo mpUserPojo);
}
