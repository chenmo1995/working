package com.fudn.minimybatisplus.serializable;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.MessageFormat;

/**
 * @author fdn
 * @since 2021-11-15 23:45
 */
@Slf4j
public class TestObjSerializeAndDeserialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializePerson();
        Person p = deSerializePerson();
        log.info(MessageFormat.format("name={0},age={1},sex={2},person={3}",
                p.getName(), p.getAge(), p.getGender(), p));
    }

    private static Person deSerializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "D:/E/working/mini-mybatis-plus/src/main/resources/person.txt"));
        Object person = ois.readObject();
        log.info("反序列化成功");
        ois.close();
        Class<?> aClass = person.getClass();
        log.info("反序列化之后的类是:{}",aClass);
        return (Person) person;
    }

    private static void serializePerson() throws IOException {
        Person person = new Person().setAge(18).setName("gyt").setGender("女");

        // ObjectOutputStream 对象输出流，将Person对象存储到Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(
                        "D:\\E\\working\\mini-mybatis-plus\\src\\main\\resources\\person.txt"));
        oos.writeObject(person);
        log.info("Person对象序列化成功！");
        oos.close();
    }
}
