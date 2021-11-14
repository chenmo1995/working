package com.javaSE.mockJunit;

/**
 * @author fdn
 * @since 2021-08-23 14:39
 */
public class EmployeeDAOTest {

    @MyBefore
    public void init(){
        System.out.println("初始化...");
    }

    @MyAfter
    public void destroy(){
        System.out.println("销毁。。。");
    }

    @MyTest
    public void testSave(){
        System.out.println("save.....");
    }

    @MyTest
    public void testDelete(){
        System.out.println("delete....");
    }





}
