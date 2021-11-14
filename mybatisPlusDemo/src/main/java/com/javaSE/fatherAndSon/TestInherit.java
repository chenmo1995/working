package com.javaSE.fatherAndSon;


/**
 * @author fdn
 * @since 2021-10-27 10:39
 */
public class TestInherit {
    public static void main(String[] args) {
        Father father = new Father();
//        Son son = new Son();
    }
    static class Father {
        public Father() {
            process();
            System.out.println("父类构造方法");
        }

        protected void process() {}
    }


    static class Son extends Father {
        public Son(){
            process();
            System.out.println("子类构造方法");
        }
        @Override
        protected void process() {
            System.out.println("子类重写父类的模版方法");
        }
    }

}

