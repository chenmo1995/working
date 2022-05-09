package com.fudn.mybatisplusdemo.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fdn
 * @since 2021-10-22 00:37
 */
@RestController
@RequestMapping("/redisTest")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/testRedis")
    public String testRedis() {
        redisTemplate.opsForValue().set("fdn:name", "fdn:gyt");
        List<String> list = redisTemplate.opsForList().range("list", 0, -1);
        String o1 = (String) redisTemplate.opsForValue().get("fdn:name");
        return o1;
    }

}
