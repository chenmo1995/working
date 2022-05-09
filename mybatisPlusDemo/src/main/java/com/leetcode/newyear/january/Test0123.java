package com.leetcode.newyear.january;

import java.util.*;

/**
 * 2034. 股票价格波动
 *
 * @author fdn
 * @since 2022-01-23 14:16
 */
public class Test0123 {

    class StockPrice {
        // timestamp,price
        Map<Integer, Integer> map;
        // price,count
        TreeMap<Integer, Integer> treeMap;

        int maxTime;

        public StockPrice() {
            map = new HashMap<>();
            treeMap = new TreeMap<>();

            maxTime = 0;
        }

        public void update(int timestamp, int price) {
            // 如果是更新已有时间的股票
            if (map.containsKey(timestamp)) {
                Integer oldPrice = map.get(timestamp);
                // TreeMap 中的对应 price 出现的次数减一
                treeMap.put(oldPrice, treeMap.get(oldPrice) - 1);
                // 如果 treeMap 中对应 price 的次数为 0 ，就 remove掉
                // 注意，如果次数不为 0 的话，不能直接 remove price，会把其他时间戳的 price 给移除掉
                if (treeMap.get(oldPrice) == 0) {
                    treeMap.remove(oldPrice);
                }
                // 更新 map
                map.put(timestamp, price);
            } else {// 新增时间对应的股票
                map.put(timestamp, price);
                treeMap.put(price, treeMap.getOrDefault(price,0) + 1);
                // 更新最新时间
                maxTime = Math.max(maxTime, timestamp);
            }
        }

        public int current() {
            return map.get(maxTime);
        }

        public int maximum() {
            return treeMap.lastKey();
        }

        public int minimum() {
            return treeMap.firstKey();
        }
    }

    public static void main(String[] args) {


        //创建TreeMap对象：
        /**
         * 默认以 key 为排序依据
         */
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        TreeMap<String, Integer> treeMap1 = new TreeMap<>((a,b)->b.compareTo(a));
        System.out.println("初始化后,TreeMap元素个数为：" + treeMap.size());

        //新增元素:
        treeMap.put("hello", 1);
        treeMap.put("world", 2);
        treeMap.put("my", 3);
        treeMap.put("name", 4);
        treeMap.put("is", 5);
        treeMap.put("huangqiuping", 6);
        treeMap.put("i", 6);
        treeMap.put("am", 6);
        treeMap.put("a", 6);
        treeMap.put("developer", 6);
        System.out.println("添加元素后,TreeMap元素个数为：" + treeMap.size());

        //遍历元素：
        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("TreeMap元素的key:" + key + ",value:" + value);
        }

        //获取所有的key：
        Set<String> keySet = treeMap.keySet();
        for (String strKey : keySet) {
            System.out.println("TreeMap集合中的key:" + strKey);
        }

        //获取所有的value:
        Collection<Integer> valueList = treeMap.values();
        for (Integer intValue : valueList) {
            System.out.println("TreeMap集合中的value:" + intValue);
        }
        System.out.println("**************************");
        //获取元素：
        //获取集合内元素key为"huangqiuping"的值
        Integer getValue = treeMap.get("huangqiuping");
        System.out.println("getValue:" + getValue);
        //获取集合内第一个元素
        String firstKey = treeMap.firstKey();
        System.out.println("firstKey:" + firstKey);
        //获取集合内最后一个元素
        String lastKey = treeMap.lastKey();
        System.out.println("lastKey:" + lastKey);
        //获取集合内的key小于"huangqiuping"的key
        String lowerKey = treeMap.lowerKey("huangqiuping");
        System.out.println("lowerKey:" + lowerKey);
        //获取集合内的key大于等于"huangqiuping"的key
        String ceilingKey = treeMap.ceilingKey("huangqiuping");
        System.out.println("ceilingKey:" + ceilingKey);
        //获取集合内的key小于等于"huangqiuping"的key
        String floorKey = treeMap.floorKey("huangqiuping");
        System.out.println("floorKey:" + floorKey);
        //获取集合的key从"a"到"huangqiuping"的元素
        SortedMap<String, Integer> sortedMap = treeMap.subMap("a", "my");
        System.out.println("sortedMap:" + sortedMap);

        //删除元素：
        //删除集合中key为"huangqiuping"的元素
        Integer removeValue = treeMap.remove("huangqiuping");
        //清空集合元素：
        treeMap.clear();

        //判断方法：
        //判断集合是否为空
        boolean isEmpty = treeMap.isEmpty();
        //判断集合的key中是否包含"huangqiuping"
        boolean isContain = treeMap.containsKey("huangqiuping");
    }

}
