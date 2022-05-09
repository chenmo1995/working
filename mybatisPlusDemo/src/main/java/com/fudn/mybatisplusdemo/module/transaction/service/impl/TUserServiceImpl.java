package com.fudn.mybatisplusdemo.module.transaction.service.impl;

import com.fudn.mybatisplusdemo.module.transaction.entity.TUser;
import com.fudn.mybatisplusdemo.module.transaction.mapper.TUserMapper;
import com.fudn.mybatisplusdemo.module.transaction.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fdn
 * @since 2022-03-25
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    /**
     * 更新 TUser
     *
     * @param tUser
     */
    @Override
    public void changeUser(TUser tUser) {
        tUser.setAge(20);
        updateById(tUser);
        int a = 10/0;
    }
}
