package com.fudn.mybatisplusdemo.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fudn.mybatisplusdemo.module.mapper.MpUserMapper;
import com.fudn.mybatisplusdemo.module.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.pojo.params.QueryUserParams;
import com.fudn.mybatisplusdemo.module.service.MpUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fdn
 * @since 2021-08-26 16:15
 */
@Service
public class MpUserServiceImpl extends ServiceImpl<MpUserMapper, MpUserPojo> implements MpUserService {

    @Resource
    private MpUserMapper userMapper;

    /**
     * 分页查询用户列表
     *
     * @param params
     * @return
     */
    @Override
    public Page<MpUserPojo> queryUserList(QueryUserParams params) {
        String id = params.getId();
        String name = params.getName();
//        int userType = params.getUserType();
        Page<MpUserPojo> page = new Page<>(params.getPageNo(), params.getLimit());
        QueryWrapper<MpUserPojo> wrapper = new QueryWrapper();
//        wrapper.lambda().
//                like(MpUserPojo::getName,name).
//                like(MpUserPojo::getId,id).
//                like(MpUserPojo::getUserType,userType);
        wrapper.lambda().
                like(MpUserPojo::getName,name).
                like(MpUserPojo::getId,id);
        Page<MpUserPojo> mpUserPojoPage = userMapper.selectPage(page, wrapper);
        return mpUserPojoPage;
    }
}
