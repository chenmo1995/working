package com.fudn.demo;

import com.fudn.minimybatisplus.demo.User;
import com.fudn.minimybatisplus.demo.UserMapper;
import com.fudn.minimybatisplus.demo.mybatisplus.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author bravo
 * @date 2021-05-29 20:14
 */
@SpringBootTest
public class MiniMyBatisTest {

    @Test
    public void test() {
        UserMapper userMapper = new UserMapper();
        User user = new User();
        user.setName("bravo1988");
        user.setAge(18);
        user.setBirthday(new Date());
        userMapper.insert(user);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(User::getName, "bravo");
        List<User> userList = userMapper.list(queryWrapper);
        System.out.println(userList);

//        User user = new User();
//        user.setName("testForUpdate");
//        QueryWrapper<User> updateWrapper = new QueryWrapper<>();
//        updateWrapper.like(User::getName, "bravo");
//        updateWrapper.eq(User::getAge, 18);
//        userMapper.updateSelective(user, updateWrapper);
//
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(User::getName, "bravo");
//        List<User> userList = userMapper.list(queryWrapper);
//        System.out.println(userList);
    }

}
