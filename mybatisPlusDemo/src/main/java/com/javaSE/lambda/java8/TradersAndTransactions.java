package com.javaSE.lambda.java8;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author fdn
 * @since 2021-12-07 16:41
 */
@Slf4j
public class TradersAndTransactions {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        methodOne();
        // (2) 交易员都在哪些不同的城市工作过？
        methodTwo();
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        methodThree();
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        methodFour();
        // (5) 有没有交易员是在米兰工作的？
        methodFive();
        // (6) 打印生活在剑桥的交易员的所有交易额。
        methodSix();
        // (7) 所有交易中，最高的交易额是多少？
        methodSeven();
        // (8) 找到交易额最小的交易。
        methodEight();
    }

    private static void methodEight() {
        Transaction transaction = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElseThrow(RuntimeException::new);


        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);

        log.info("methodEight:{},{}", transaction,smallestTransaction);
    }

    private static void methodSeven() {
        int max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(0);
        log.info("methodSeven:{}", max);

    }

    private static void methodSix() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    private static void methodFive() {
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        log.info("methodFive:{}", milan);
    }

    private static void methodFour() {
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        String joining = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        log.info("methodFour:{},{}", reduce, joining);
    }

    private static void methodThree() {
        List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> Objects.equals(trader.getCity(), "Cambridge"))
                .distinct()
                .sorted(comparing(trader -> trader.getName()))
                .collect(toList());
        log.info("methodThree:{}", cambridge);
    }

    private static void methodTwo() {
        List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        log.info("methodTwo:{}", collect);
    }

    private static void methodOne() {
        List<Transaction> collect = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(toList());
        log.info("methodOne:{}", collect);
    }
}

@Data
class Trader {
    private final String name;
    private final String city;

}

@Data
class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
}
