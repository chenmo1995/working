package com.fudn.minimybatisplus.demo;

import com.fudn.minimybatisplus.demo.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author bravo1988
 * @date 2021-05-29 20:05
 */
@Data
@TableName("t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
}
