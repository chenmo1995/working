package com.fudn.mybatisplusdemo.module.transaction.service;

import com.fudn.mybatisplusdemo.module.transaction.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fdn
 * @since 2022-03-25
 */
public interface ITUserService extends IService<TUser> {

    /**
     * 更新 TUser
     * @param tUser
     */
    void changeUser(TUser tUser);
}
