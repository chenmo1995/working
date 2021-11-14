package com.javaSE.lambda.exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.javaSE.lambda.stream.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream练习的测试数据
 * <p>
 * -filter()、map()如果逻辑过长，最好抽取函数
 * -IntStream、LongStream、DoubleStream在统计方面比Stream方法更丰富，更好用
 * -collect()是最强大的，但一般掌握上面6种情景问题不大
 * -去重的原理是利用hashCode()和equals()来确定两者是否相同，无论是自定义对象还是String、Integer等常用内置对象，皆是如此
 * -排序的原理是，要么自身实现Comparable接口，要么传入Comparator对象，总之要明确比较的规则
 * -平时可能觉得skip()、limit()用不到，但需要截取List或者内存分页时，可以尝试一下
 * -尽量用Stream代替List原生操作，代码健壮性和可读性都会提升一个台阶
 *
 * @author fdn
 * @since 2021-09-07 16:31
 */
public class StreamTest {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("iron", 17, "宁波", 888.8));
    }

    public static void main(String[] args) throws JsonProcessingException {
//        mapTest();
//        getNameAndAddress();
//        filter18();
//        filterAgeAndSalary();
//        getCount();
//        findSome();
//        compareTest();
//        listToMap();
//        groupTest();
//        partitioningTest();
//        sortedTest();
//        limitTest();
        forEachTest();
    }

    private static void forEachTest() {
        list.stream().forEach(System.out::println);
        list.forEach(System.out::println);
        for (Person person : list) {
            System.out.println(person);
        }
    }

    /**
     * 获取所有的Person的名字
     *
     * @return
     */
    public static List<String> mapTest() {
        List<String> personNames = list.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(personNames);
        return personNames;
    }

    /**
     * 获取一个List，每个元素的内容为：{name}来自{address}
     *
     * @return
     */
    public static List<String> getNameAndAddress() {
        // 这里就没法用方法引用了（没有现成可用的方法），只能用Lambda表达式
        List<String> personNames = list.stream().map(person -> person.getName() + "来自" + person.getAddress()).collect(Collectors.toList());
        System.out.println(personNames);
        return personNames;
    }

    /**
     * 过滤出年龄大于等于18的Person
     *
     * @return
     */
    public static List<Person> filter18() {
        List<Person> personList = list.stream().filter(person -> person.getAge() >= 18).collect(Collectors.toList());
        System.out.println(personList);
        return personList;
    }

    /**
     * 过滤出年龄大于等于18 并且 月薪大于等于888.8 并且 来自杭州的Person
     *
     * @return
     */
    public static List<Person> filterAgeAndSalary() {
        List<Person> personList = list.stream().filter(person ->
                person.getAge() >= 18 && person.getSalary() >= 888.8 && "杭州".equals(person.getAddress()))
                .collect(Collectors.toList());
        System.out.println(personList);

        // 较好的写法：当我们需要给filter()、map()等函数式接口传递Lambda参数时，逻辑如果很复杂，最好抽取成方法，优先保证可读性
        List<Person> personList2 = list.stream()
                .filter(StreamTest::isRichAdultInHangZhou) // 改为方法引用，见名知意，隐藏琐碎的细节
                .collect(Collectors.toList());
        System.out.println(personList);
        return personList;
    }

    /**
     * 是否杭州有钱人
     *
     * @param person
     * @return
     */
    private static boolean isRichAdultInHangZhou(Person person) {
        return person.getAge() >= 18
                && person.getSalary() > 888.8
                && "杭州".equals(person.getAddress());
    }


    /**
     * 统计
     * 获取年龄的min、max、sum、average、count。
     * <p>
     * 很多人看到这个问题，会条件反射地想到先用map()把Person降到Age纬度，
     * 然后看看有没有类似min()、max()等方法，思路很合理。
     * <p>
     * 然后会发现：map()后只提供了max()、min()和count()三个方法（且max、min需要传递Comparator对象），
     * 没有average()和count()。
     * <p>
     * 如果你想要做通用的数据处理，直接使用map()等方法即可，此时返回的是Stream，提供的都是通用的操作。
     * 但如果要统计的数据类型恰好是int、long、double，
     * 那么使用mapToXxx()方法转为具体的Stream类型后，方法更多更强大，处理起来更为方便！
     */
    public static void getCount() {
        list.stream().max(Comparator.comparing(Person::getAge));

        OptionalInt max = list.stream().mapToInt(Person::getAge).max();
        OptionalInt min = list.stream().mapToInt(Person::getAge).min();
        int sum = list.stream().mapToInt(Person::getAge).sum();
        OptionalDouble average = list.stream().mapToInt(Person::getAge).average();
        System.out.println(max + "***" + min + "***" + sum + "***" + average);
    }

    /**
     * 查找
     * 查找其实分为两大类操作：
     * 查找并返回目标值，以findFirst()为代表
     * 告诉我有没有即可，无需返回目标值，以anyMatch()为代表
     */
    public static void findSome() {
        Optional<Person> first = list.stream().filter(person -> "宁波111".equals(person.getAddress()))
                .findFirst();
        first.ifPresent(System.out::println);

        // 但有时候你并不关心符合条件的是哪个或哪些元素，只想知道有没有，此时anyMatch()更合适，代码会精炼很多
        boolean b = list.stream().anyMatch(person -> "宁波111".equals(person.getAddress()));
        System.out.println(b);
    }

    /**
     * collect()
     */
    public static void collectorTest() {
        List<String> list = StreamTest.list.stream()
                .filter(person -> person.getAge() >= 18)
                .sorted(Comparator.comparingInt(Person::getAge))
                .map(Person::getName)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    public static void compareTest() {
        List<Integer> collect = Arrays.asList(1, 5, 3, 6, 4, 6, 8, 4).stream()
                .sorted(Comparator.comparingInt(Integer::intValue))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void listToMap() {
        Map<String, Person> collect = list.stream()
                .collect(Collectors.toMap(
                        Person::getName,// 以name作为key
                        person -> person,// person->person表示保留整个person作为value
                        (pre, next) -> pre // (pre, next) -> pre)表示key冲突时保留旧值
                ));
        System.out.println(collect);
    }

    public static void listToSet() {
        // String、Integer这些类本身重写了hashCode()和equals()，可以直接toSet()
        Set<String> names = list.stream().map(Person::getName).collect(Collectors.toSet());
        System.out.println(names);

        // 如果你要对自定义的对象去重，比如Person，那么你必须重写hashCode()和equals()
        Set<Person> persons = list.stream().collect(Collectors.toSet());
        System.out.println(persons);

        // 一般来说，用到Collectors.toSet()之前，也是filter()等一顿操作，最后希望去重。像上面那样单纯地想得到Set，可以简单点
        Set<Person> anotherPersons = new HashSet<>(list);
        System.out.println(anotherPersons);

    }

    /**
     * 去重
     */
    public static void distinctTest() {
        //方式1
        List<Person> persons = list.stream().distinct().collect(Collectors.toList());
        System.out.println(persons);

        //方式2
        // 先通过Map去重，只保留key不同的对象。
        Map<String, Person> personMap = list.stream().collect(Collectors.toMap(
                Person::getName,   // 用person.name做key，由于key不能重复，即根据name去重
                p -> p,            // map的value就是person对象本身
                (pre, next) -> pre // key冲突策略：key冲突时保留前者（根据实际需求调整）
        ));

        // 然后收集value即可（特别注意，hash去重后得到的person不保证原来的顺序）
        List<Person> peoples = new ArrayList<>(personMap.values());
        System.out.println(peoples);

    }

    /**
     * 分组
     */
    public static void groupTest() throws JsonProcessingException {
        Map<String, List<Person>> collect = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress));
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(collect));

        // groupingBy()还可以传入第二个参数，指定如何收集元素
        Map<String, Set<Person>> result = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress, Collectors.toSet()));
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));

        // 进阶版版
        //groupingBy()的最终结果是Map，key是分组的字段，value是属于该分组的所有元素集合，默认是List。
        //可以把Collectors.toSet()替换成Collectors.mapping()，然后进行嵌套,可以订制value
        Map<String, List<Integer>> result1 = list.stream().collect(Collectors.groupingBy(
                Person::getAddress,                                        // 以Address分组
                Collectors.mapping(Person::getAge, Collectors.toList()))   // mapping()的做法是先映射再收集
        );
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result1));
    }

    public static void partitioningTest() throws JsonProcessingException {
        //partitioningBy也支持嵌套
        Map<Boolean, List<Person>> collect = list.stream()
                .collect(Collectors.partitioningBy(StreamTest::condition));
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(collect));
    }

    private static boolean condition(Person person) {
        // 年龄大于18，且来自杭州
        return person.getAge() > 18
                && "杭州".equals(person.getAddress());
    }

    /**
     * 排序
     */
    public static void sortedTest() {
        //这里的sorted()不用传入比较规则是因为Person实现了Comparable接口的compare方法，自身可以进行比较
//        List<Person> collect = list.stream()
//                .sorted().collect(Collectors.toList());
//        System.out.println(collect);
        List<Person> collect = list.stream()
                .sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        System.out.println(collect);

    }

    /**
     * 截取
     */
    public static void limitTest() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");

        List<String> limit3 = list.stream().limit(3).collect(Collectors.toList());
        // 超出实际长度也不会报错
        List<String> limit5 = list.stream().limit(5).collect(Collectors.toList());
        List<String> range3_4 = list.stream().skip(2).limit(2).collect(Collectors.toList());
        // 超出实际长度也不会报错
        List<String> range3_5 = list.stream().skip(2).limit(3).collect(Collectors.toList());

        System.out.println(limit3 + " " + limit5 + " " + range3_4 + " " + range3_5);
    }

    /**
     * 多用stream
     * 要把AList和BList合并，BList的元素在前，合并后的List不能有重复元素（过滤AList），且不改变两个List原有元素的顺序。
     * name属性相同就算做重复
     */
    public static List<Person> useStream(List<Person> aList, List<Person> bList) {
        //将bList转成Map<name,Person>
        Map<String, Person> bPersonMap = ConvertUtil.listToMap(bList, Person::getName);
        //获取aList中不与bList重复的元素
        List<Person> collect = aList.stream()
                .filter(item -> !bPersonMap.containsKey(item.getName()))
                .collect(Collectors.toList());
        bList.addAll(collect);
        return bList;
    }

    /**
     * 关于统计
     */
    public static void statisticsTest() {
        // 统计其实有三大类：stream直接统计、IntStream等具体的Stream统计、collect()中统计

        // Stream直接统计：min/max/count
        Optional<Person> collect1 = list.stream().min(Comparator.comparingInt(Person::getAge));
        Optional<Person> collect2 = list.stream().max(Comparator.comparingInt(Person::getAge));
        long count = list.stream().count();

        // IntStream/LongStream/DoubleStream统计：min/max/count/average/sum/summaryStatistics
        OptionalInt min = list.stream().mapToInt(Person::getAge).min();
        OptionalInt max = list.stream().mapToInt(Person::getAge).max();
        long count1 = list.stream().mapToInt(Person::getAge).count();
        OptionalDouble average = list.stream().mapToInt(Person::getAge).average();
        int sum = list.stream().mapToInt(Person::getAge).sum();
        IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt(Person::getAge).summaryStatistics();


        // collect()统计，和IntStream们类似，可以被优化为上面两种写法，不常用
        list.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getAge)));

        list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));

        list.stream().collect(Collectors.averagingDouble(Person::getAge));
        list.stream().collect(Collectors.averagingLong(Person::getAge));
        list.stream().collect(Collectors.averagingInt(Person::getAge));

        list.stream().collect(Collectors.summingDouble(Person::getAge));
        list.stream().collect(Collectors.summingDouble(Person::getAge));
        list.stream().collect(Collectors.summingInt(Person::getAge));

        list.stream().collect(Collectors.counting());

        list.stream().collect(Collectors.summarizingDouble(Person::getAge));
        list.stream().collect(Collectors.summarizingInt(Person::getAge));
        list.stream().collect(Collectors.summarizingLong(Person::getAge));
    }

    public static void streamToLinkedList() {
        LinkedList<Person> collect = list.stream()
                .filter(person -> person.age > 18)
                .sorted(Comparator.comparingInt(Person::getAge))
                .skip(2)
                .limit(3)
                .collect(Collectors.toCollection(()->new LinkedList<>()));
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    static class Person implements Comparable<Person> {
        private String name;
        private Integer age;
        private String address;
        private Double salary;


        @Override
        public int compareTo(Person anotherPerson) {
            //降序
            return anotherPerson.getAge() - this.getAge();
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Person person = (Person) o;
//            return name.equals(person.name);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(name);
//        }

        //        @Override
//        public String toString() {
//            return "Person{" +
//                    "name='" + name + '\'' +
//                    ", age=" + age +
//                    ", address='" + address + '\'' +
//                    ", salary=" + salary +
//                    '}';
//        }
    }

}
