package com.fudn.mybatisplusdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fudn.mybatisplusdemo.common.resultReturn.RestResponse;
import com.fudn.mybatisplusdemo.pojo.dto.AdduserDTO;
import com.fudn.mybatisplusdemo.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.pojo.params.QueryUserParams;
import com.fudn.mybatisplusdemo.service.MpUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "新增用户接口")
    public RestResponse<MpUserPojo> addUser(@RequestBody @Valid AdduserDTO adduserDTO) {
        AdduserDTO fdn = AdduserDTO.builder()
                .age(18)
                .name("FDN")
                .userType(0)
                .build();
        AdduserDTO gyt = fdn.toBuilder()
                .name("GYT")
                .age(18)
                .build();

        log.info("并没有往数据库中插入数据哈，user:{}", gyt);
        log.info("并没有往数据库中插入数据哈，传来的数据:{}", adduserDTO);

        MpUserPojo mpUserPojo = new MpUserPojo()
                .setName(adduserDTO.getName())
                .setAge(adduserDTO.getAge())
                .setUserType(adduserDTO.getUserType());
        return RestResponse.ok(mpUserPojo);
    }

    @PostMapping("/queryUserList")
    @ApiOperation(value = "分页查询用户列表")
    public RestResponse<Page<MpUserPojo>> queryUserList(QueryUserParams params) {
        Page<MpUserPojo> userList = userService.queryUserList(params);
        return RestResponse.ok(userList);
    }


}
