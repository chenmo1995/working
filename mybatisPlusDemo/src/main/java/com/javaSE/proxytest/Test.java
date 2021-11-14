package com.javaSE.proxytest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Class<Calculate> calculateClass = Calculate.class;


        Class<?> proxyClass = Proxy.getProxyClass(calculateClass.getClassLoader(), calculateClass);

        System.out.println(CalculateImpl.class.getName());
        System.out.println(proxyClass.getName());

        //代理类的构造器
        Constructor<?>[] constructor = proxyClass.getDeclaredConstructors();
        System.out.println("-------------构造器--------------");
        printClassInfo(constructor);

        //代理对象的方法
        Method[] methods = proxyClass.getDeclaredMethods();
        Method[] methods1 = proxyClass.getMethods();
        System.out.println("-------------方法--------------");
        printClassInfo(methods);
        System.out.println("-------------方法--------------");
        printClassInfo(methods1);

    }








    public static void printClassInfo(Executable[] targets) {
        for (Executable target : targets) {
            // 构造器/方法名称
            String name = target.getName();
            StringBuilder sBuilder = new StringBuilder(name);
            // 拼接左括号
            sBuilder.append('(');
            Class<?>[] clazzParams = target.getParameterTypes();
            // 拼接参数
            for (Class<?> clazzParam : clazzParams) {
                sBuilder.append(clazzParam.getName()).append(',');
            }
            //删除最后一个参数的逗号
            if (clazzParams.length != 0) {
                sBuilder.deleteCharAt(sBuilder.length() - 1);
            }
            //拼接右括号
            sBuilder.append(')');
            //打印 构造器/方法
            System.out.println(sBuilder.toString());
        }
    }
}
