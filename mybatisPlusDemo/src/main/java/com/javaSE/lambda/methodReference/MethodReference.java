package com.javaSE.lambda.methodReference;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fdn
 * @since 2021-08-13 09:51
 */
public class MethodReference {

    private static final List<Person> list = new ArrayList<>();

    static {
        list.add(new Person(26));
        list.add(new Person(24));
        list.add(new Person(30));
    }

    public static void main(String[] args) {
        System.out.println(list);
        list.sort(Person::compare);
        System.out.println(list);
        List<Person> list1 = new ArrayList<>();
        //list1.add(element)
        List<Boolean> collect = list.stream().map(list1::add).collect(Collectors.toList());
        System.out.println(collect);

    }

    @Data
    @AllArgsConstructor
    static class Person {
        private Integer age;

        public static int compare(Person p1, Person p2) {

            return p1.getAge() - p2.getAge();
        }
    }
}
