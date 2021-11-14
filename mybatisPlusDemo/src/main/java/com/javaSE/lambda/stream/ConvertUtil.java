package com.javaSE.lambda.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author fdn
 * @since 2021-08-17 18:19
 */
public class ConvertUtil {

    public ConvertUtil() {
    }

    /**
     * 将List转变成Map
     *
     * @param list         原数据
     * @param keyExtractor key的抽取规则
     * @param <K>          key
     * @param <V>          value
     * @return Map
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for (V v : list) {
            K key = keyExtractor.apply(v);
            if (key == null) {
                continue;
            }
            map.put(key, v);
        }
        return map;
    }

    /**
     * List转变成Map，可以指定Map值的过滤规则
     *
     * @param list         原数据
     * @param keyExtractor key的抽取规则
     * @param predicate    value的过滤规则
     * @param <K>          key
     * @param <V>          value
     * @return 转变之后的Map
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor, Predicate<V> predicate) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for (V v : list) {
            K key = keyExtractor.apply(v);
            if (key == null || !predicate.test(v)) {
                continue;
            }
            map.put(key, v);
        }
        return map;
    }

    /**
     * 将List映射成List，如将List<Person> personList映射成List<String> nameList
     *
     * @param originalList 原list
     * @param mapper       映射规则
     * @param <T>          原数组元素类型
     * @param <R>          新数组元素类型
     * @return 返回list
     */
    public static <T, R> List<R> resultToList(List<T> originalList, Function<T, R> mapper) {
        if (originalList == null || originalList.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> list = new ArrayList<>(originalList.size());
        for (T t : originalList) {
            R result = mapper.apply(t);
            if (result == null) {
                continue;
            }
            list.add(result);
        }
        return list;
    }

    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     * 可以指定过滤规则
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param predicate  过滤规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return
     */
    public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper, Predicate<T> predicate) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>(originList.size());
        for (T originElement : originList) {
            R newElement = mapper.apply(originElement);
            if (newElement == null || !predicate.test(originElement)) {
                continue;
            }
            newList.add(newElement);
        }
        return newList;
    }

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        Map<String, Person> nameToPersonMap = listToMap(list, Person::getName);
        for (Map.Entry<String, Person> stringPersonEntry : nameToPersonMap.entrySet()) {
            System.out.println(stringPersonEntry.getValue().getAddress());
            System.out.println(stringPersonEntry.getKey()+"****"+stringPersonEntry.getValue());
        }

//        System.out.println(nameToPersonMap);

        Map<String, Person> personGt18 = listToMap(list, Person::getName, person -> person.getAge() >= 18);
        System.out.println(personGt18);
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
