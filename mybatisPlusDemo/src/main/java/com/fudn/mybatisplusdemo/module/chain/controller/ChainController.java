package com.fudn.mybatisplusdemo.module.chain.controller;

import com.fudn.mybatisplusdemo.module.chain.service.ChainContextHandler;
import com.fudn.mybatisplusdemo.module.chain.dto.ChainOrderDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fdn
 * @since 2022-04-27 00:24
 */
@RestController
@RequestMapping("/chain")
public class ChainController {

    @Autowired
    private ChainContextHandler chainContextHandler;

    @PostMapping("/test")
    @ApiOperation("测试链式处理")
    public String test(ChainOrderDTO orderdto) {
        chainContextHandler.handlerRequest(orderdto);
        return "success";
    }
}
