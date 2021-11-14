package com.fudn.mybatisplusdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author fdn
 * @since 2021-10-22 00:37
 */
@RestController
@RequestMapping("/redisTest")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public String testRedis(){
        redisTemplate.opsForValue().set("fdn:name","fdn:gyt");
        String o1 = (String) redisTemplate.opsForValue().get("fdn:name");
        return o1;
    }

}
