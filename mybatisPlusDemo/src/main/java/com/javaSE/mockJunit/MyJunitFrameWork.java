package com.javaSE.mockJunit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个就是注解三部曲中最重要的：读取注解并操作
 * 相当于我们使用Junit时看不见的那部分（在隐秘的角落里帮我们执行标注了@Test的方法）
 *
 * @author fdn
 * @since 2021-08-23 14:43
 */
public class MyJunitFrameWork {
    public static void main(String[] args) throws Exception {
        //1.先找到测试类的字节码
        Class<EmployeeDAOTest> clazz = EmployeeDAOTest.class;
        EmployeeDAOTest instance = clazz.newInstance();

        //2.获取EmployeeDAOTest中所有的公共方法
        Method[] methods = clazz.getMethods();

        //3.迭代出每一个Method对象，判断哪些方法上使用了@MyBefore/@MyAfter/@MyTest注解
        List<Method> myBeforeList = new ArrayList<>();
        List<Method> myAfterList = new ArrayList<>();
        List<Method> myTestList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                //存储使用了@MyBefore注解的方法对象
                myBeforeList.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                //存储使用了@MyAfter注解的方法对象
                myAfterList.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                //存储使用了@MyTest注解的方法对象
                myTestList.add(method);
            }
        }

        // 执行测试方法
        for (Method testMethod : myTestList) {
            // 先执行@Before方法
            for (Method beforeMethod : myBeforeList) {
                beforeMethod.invoke(instance, null);
            }
            // 测试方法
            testMethod.invoke(instance);
            // 再执行@After方法
            for (Method afterMethod : myAfterList) {
                afterMethod.invoke(instance);
            }
        }
    }
}
