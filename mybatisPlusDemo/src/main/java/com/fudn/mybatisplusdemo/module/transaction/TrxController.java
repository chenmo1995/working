package com.fudn.mybatisplusdemo.module.transaction;

import com.fudn.mybatisplusdemo.common.resultReturn.RestResponse;
import com.fudn.mybatisplusdemo.module.base.pojo.dto.AdduserDTO;
import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.base.service.MpUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author fdn
 * @since 2022-03-25 15:16
 */
@RestController
@Slf4j
@Api(tags = {"测试事务"})
public class TrxController {

    @Autowired
    MpUserService userService;

    @Autowired
    private TrxService trxService;

    @PostMapping("/trx")
    @ApiOperation(value = "测试事务是否回滚")
    public RestResponse<String> trxTest() {
        trxService.test();

        return RestResponse.ok("");
    }



}
