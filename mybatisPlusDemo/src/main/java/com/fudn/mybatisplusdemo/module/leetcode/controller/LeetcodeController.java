package com.fudn.mybatisplusdemo.module.leetcode.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fudn.mybatisplusdemo.common.resultReturn.RestResponse;
import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.base.pojo.params.QueryUserParams;
import com.fudn.mybatisplusdemo.module.leetcode.service.LeetcodeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fdn
 * @since 2022-02-16 17:53
 */
@RestController
@RequestMapping("/leetcode")
@Slf4j
public class LeetcodeController {

    @Autowired
    private LeetcodeService leetcodeService;

    @PostMapping("/detail")
    @ApiOperation(value = "获取登录用户详情")
    public RestResponse queryUserDetail() {

        return RestResponse.ok();
    }


}
