package com.javaSE.lambda.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author fdn
 * @since 2021-08-12 18:54
 */
public class MockStream {
    public static void main(String[] args) throws JsonProcessingException {
        Person person1 = new Person("付达南",22);
        Person person2 = new Person("付达西",26);
        Person person3 = new Person("付达北",30);

        MyList<Person> myList = new MyList<>();
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);

//        MyList<String> map = myList.filter(a -> a.getAge() > 25).map(a -> a.getName());
        MyList<String> map = myList.filter(a -> a.getAge() > 25).map(Person::getName);
        prettyPrint(map.getList());

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);

//        List<String> collect = list.stream().filter(a -> a.getAge() > 25).map(Person::getName).collect(Collectors.toList());
        list.sort(Comparator.comparingInt(Person::getAge));

        System.out.println(list);

    }

    private static void prettyPrint(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        System.out.println(s);
    }
}
