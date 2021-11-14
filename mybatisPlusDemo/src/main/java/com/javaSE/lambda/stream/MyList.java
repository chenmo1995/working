package com.javaSE.lambda.stream;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2021-08-12 18:33
 */
@Getter
public class MyList<T> {

    private List<T> list = new ArrayList<>();

    public boolean add(T e) {
        return list.add(e);
    }

    /**
     * 给MyList传递具体的判断规则，然后MyList把内部现有符合判断（true）的元素集返回
     *
     * @param predicate
     * @return
     */
    public MyList<T> filter(Predicate<T> predicate) {
        MyList<T> filterList = new MyList<>();

        Predicate pre = a -> a.toString().length()>0;
        Predicate pre1 = new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };

        for (T t : list) {
            if (predicate.test(t)) {
                //收集判断为true的元素
                filterList.add(t);
            }
        }
        return filterList;
    }

    public <R> MyList<R> map(Function<T, R> mapper) {
        MyList<R> mapList = new MyList<>();
        for (T t : list) {
            R r = mapper.apply(t);
            mapList.add(r);
        }
        return mapList;
    }


    /**
     * 定义一个断言式函数式接口
     *
     * @param <T>
     */
    @FunctionalInterface
    interface Predicate<T> {

        /**
         * 定义了一个test()方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
         *
         * @param t
         * @return
         */
        boolean test(T t);
    }

    interface Function<E, R> {

        /**
         * 定义一个apply()方法，接收一个E，返回一个R，也就是把E映射成R
         * E(Enter)表示入参类型，R(Return)表示返回值类型
         *
         * @param e
         * @return
         */
        R apply(E e);

    }

}
