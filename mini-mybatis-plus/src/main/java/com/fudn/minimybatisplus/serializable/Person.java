package com.fudn.minimybatisplus.serializable;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fdn
 * @since 2021-11-15 23:43
 */
@Data
@Accessors(chain = true)
public class Person implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5809782578272943999L;
    private int age;
    private String name;
    private String gender;
}
