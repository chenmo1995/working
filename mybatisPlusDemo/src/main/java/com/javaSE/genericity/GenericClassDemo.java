package com.javaSE.genericity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类型越高，兼容性越强（可以强转），可用方法越少    如Object
 * 类型越低，越明确（强转越容易出错），可用方法越多     如String
 *
 * @author fdn
 * @since 2021-08-24 11:02
 */
public class GenericClassDemo {

    public static void main(String[] args) {
        List<Number> integerList = new ArrayList<>();
        integerList.add(100);
        integerList.add(1.1);
        print(integerList);

        List<? extends Number> list = integerList;
//        list.add(1)

        List list1 = new ArrayList();
        list1.add(1);
        list1.add("1");
        list1.add(new ObjectArray());

        //简单泛型强制左右两边泛型一致
//        List<Object> list3 = new ArrayList<Integer>();
        List<?> list4 = new ArrayList<Integer>();


        List<Long> longList = new ArrayList<>();
        print(longList);
//        List<Object> list = new ArrayList<Integer>();

        // 直接往String[]存Integer会编译错误
        String[] strings = new String[3];
        strings[0] = "a";
        strings[1] = "b";
//        strings[2] = 100; // COMPILE ERROR!

        // 但数组允许String[]赋值给Object[]
        Object[] objects = strings;
        // 这样就能通过编译了，但运行期会抛异常：ArrayStoreException
        objects[2] = "100";
        System.out.println(Arrays.asList(objects));

    }

    public static void print(List<? extends Number> list) {
        // 打印...
    }


    void study(){

    }
    void study(List<Object> list,Integer integer){

    }
    void study(List<String> list){

    }
}
