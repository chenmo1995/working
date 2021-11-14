package com.fudn.controller;

import com.fudn.spring.context.ApplicationContext;
import com.fudn.spring.context.support.ClassPathXmlApplicationContext;

/**
 * @author fdn
 * @since 2021-09-17 01:32
 */
public class UserController {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

    }
}
