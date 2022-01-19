package com.javaSE.lambda.java8;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author fdn
 * @since 2021-12-07 17:39
 */
@Slf4j
public class PythagoreanNumber {

    public static void main(String[] args) {
        triangle();
        fibonacci();
    }

    private static void fibonacci() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t->log.info(String.valueOf(t[0])));

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
    }

    private static void triangle() {
        Stream<double[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()//从数值流映射成一般流，因为IntStream上的操作只能产生原始整数
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))})
                                .filter(t -> t[2] % 1 == 0));
        stream.limit(5).forEach(doubles -> log.info(doubles[0] + "," + doubles[1] + "," + doubles[2]));
    }

}
