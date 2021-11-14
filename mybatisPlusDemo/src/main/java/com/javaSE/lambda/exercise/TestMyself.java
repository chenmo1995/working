package com.javaSE.lambda.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fdn
 * @since 2021-09-18 11:50
 */
public class TestMyself {
    /**
     * 需求：
     * 1.要求返回所有的key，格式为 list<Long>      提示:keyset
     * 2.要求最终返回所有value，格式为 List<Long>   提示:flatMap()，Function需要啥你就转成啥
     *
     * @param args
     */
    public static void main(String[] args) {
        flatMapTest();
    }

    private static void flatMapTest() {
        Map<Long, List<Long>> map = new HashMap<>();
        map.put(1L, new ArrayList<>(Arrays.asList(1L, 2L, 3L)));
        map.put(2L, new ArrayList<>(Arrays.asList(4L, 5L, 6L)));

        Set<Long> longs = map.keySet();
        List<Long> result1 = new ArrayList<>(longs);


        /**
         * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
         * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
         *  使用flatMap方法的效果是，各个数组并不是分别映射一个流，而是映射成流的内容，
         */
        //List<Long> result2 = map.values().stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        Stream<List<Long>> stream = map.values().stream();
        List<Long> result2 = map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    /**
     * 1.要求分组统计出各个城市的年龄总和，返回格式为 Map<String, Integer>。
     * 2.要求得到Map<城市, List<用户工资>>
     */
    public static void test2() {
        Map<String, Integer> collect = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress, Collectors.summingInt(Person::getAge)));
        Map<String, List<Double>> collect1 = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress, Collectors.mapping(Person::getSalary, Collectors.toList())));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;
    }

}
