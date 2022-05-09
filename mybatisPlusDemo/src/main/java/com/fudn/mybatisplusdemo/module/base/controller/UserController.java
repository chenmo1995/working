package com.fudn.mybatisplusdemo.module.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fudn.mybatisplusdemo.common.resultReturn.RestResponse;
import com.fudn.mybatisplusdemo.module.base.pojo.dto.AdduserDTO;
import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.base.pojo.params.QueryUserParams;
import com.fudn.mybatisplusdemo.module.base.service.MpUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author fdn
 * @since 2021-08-30 16:40
 */
@RestController
@Slf4j
@Api(tags = {"用户接口"})
public class UserController {

    @Resource
    MpUserService userService;

    /**
     * @param adduserDTO
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "新增用户接口")
    @Validated
    public RestResponse<MpUserPojo> addUser(@RequestBody @Valid AdduserDTO adduserDTO) {
        AdduserDTO fdn = new AdduserDTO()
                .setAge(18)
                .setName("FDN")
                .setUserType(0);
        AdduserDTO gyt = fdn.setName("GYT").setAge(18);

        log.info("并没有往数据库中插入数据哈，user:{}", gyt);
        log.info("并没有往数据库中插入数据哈，传来的数据:{}", adduserDTO);

        MpUserPojo mpUserPojo = new MpUserPojo()
                .setName(adduserDTO.getName())
                .setAge(adduserDTO.getAge())
                .setUserType(adduserDTO.getUserType());
        userService.saveOrUpdate(mpUserPojo);
        return RestResponse.ok(mpUserPojo);
    }

    @PostMapping("/queryUserList")
    @ApiOperation(value = "分页查询用户列表")
    public RestResponse<Page<MpUserPojo>> queryUserList(QueryUserParams params) {
        Page<MpUserPojo> userList = userService.queryUserList(params);
        return RestResponse.ok(userList);
    }


}
