package com.fudn.minimybatisplus.serializable;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fdn
 * @since 2021-11-15 23:45
 */
@Slf4j
public class TestLambdaSerializeAndDeserialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {


        serializeFuFunction();
        deSerialize();
    }

    private static void deSerialize() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "D:\\E\\working\\mini-mybatis-plus\\src\\main\\resources\\lambda.txt"));
        Object o = ois.readObject();
        log.info("反序列化之后的类型：{};值是：{}", o.getClass(), o);
        ois.close();
    }

    private static void serializeFuFunction() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FuFunction fuFunction = () -> log.info("函数式接口的方法");
        fuFunction.test();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "D:\\E\\working\\mini-mybatis-plus\\src\\main\\resources\\lambda.txt"));
        oos.writeObject(fuFunction);
        Method[] declaredMethods = fuFunction.getClass().getDeclaredMethods();
        Method writeReplace = fuFunction.getClass().getDeclaredMethod("writeReplace");
        writeReplace.setAccessible(true);
        Object invoke = writeReplace.invoke(fuFunction, null);
        Class<?> aClass = invoke.getClass();//java.lang.invoke.SerializedLambda
        log.info("序列化出去了:{}；类型是:{}", fuFunction, fuFunction.getClass());
        oos.close();
    }


}

/**
 * 待序列化的函数式接口
 */
@FunctionalInterface
interface FuFunction extends Serializable {
    void test();

}
