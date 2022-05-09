package com.javaSE.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-03-04 14:19
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        People people = new People();
        People clone = (People) people.clone();
        System.out.println(clone == people);
        System.out.println(clone.equals(people));
        System.out.println(people.hashCode()+":"+clone.hashCode());
        System.out.println(System.identityHashCode(people));
        System.out.println(System.identityHashCode(clone));

        People[] peoples = new People[3];
//        peoples[0] = new People(1, "zhangsan");
        peoples[0] = new People(1);

        People[] people1 = Arrays.copyOf(peoples, 3);
        System.out.println(Arrays.toString(peoples));
        System.out.println(Arrays.toString(people1));
        peoples[0].setId(2);
//        peoples[0].setName("lisi");
        System.out.println(Arrays.toString(peoples));
        System.out.println(Arrays.toString(people1));

        int[] arr = new int[1];
        arr[0] = 1;
        int[] copyArr = Arrays.copyOf(arr, 1);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copyArr));
        arr[0] = 2;
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copyArr));


    }


    @AllArgsConstructor
    @NoArgsConstructor
    static class People implements Cloneable {
        private int id;
//        private String name;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
