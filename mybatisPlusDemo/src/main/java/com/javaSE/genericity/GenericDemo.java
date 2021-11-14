package com.javaSE.genericity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2021-09-18 17:29
 */
public class GenericDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        // 编译器会阻止
        // list.add(333);

        // 但泛型约束只存在于编译期，底层仍是Object，所以运行期可以往List存入任何类型的元素
        Method addMethod = list.getClass().getDeclaredMethod("add", Object.class);
        addMethod.invoke(list, 333);

        // 打印输出观察是否成功存入Integer（注意用Object接收）
        for (Object obj : list) {
            System.out.println(obj);
        }
//
//        UserDao userDao = new UserDao();
//        User user = userDao.get(new User());
//        List<User> list = userDao.getList(new User());
    }
}

class BaseDao<T> {

    public T get(T t){
        return t;
    }

    public List<T> getList(T t){
        return new ArrayList<>();
    }
}


class UserDao extends BaseDao<User> {

}

class User{

}
