package com.javaSE.enumeration.mock;

import lombok.Data;
import lombok.Getter;

/**
 * @author fdn
 * @since 2021-08-31 00:24
 */
public class EmployeeDemo {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setRestDay(WeekDay.FRIDAY);
    }
}

@Data
class Employee {
    /**
     * 制定员工的休息日
     */
    private WeekDay restDay;
}

/**
 * 用常量类改进，限制restDay输入
 */
//@Data
//class WeekDay{
//    public static final Integer MONDAY = 1;
//    public static final Integer TUESDAY = 2;
//    public static final Integer WEDNESDAY = 3;
//    public static final Integer THURSDAY = 4;
//    public static final Integer FRIDAY = 5;
//    public static final Integer SATURDAY = 6;
//    public static final Integer SUNDAY = 7;
//}
@Getter
class WeekDay {
    private Integer code;
    private String desc;

    private WeekDay(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final WeekDay MONDAY = new WeekDay(1, "礼拜一");
    public static final WeekDay TUESDAY = new WeekDay(2, "礼拜二");
    public static final WeekDay WEDNESDAY = new WeekDay(3, "礼拜三");
    public static final WeekDay THURSDAY = new WeekDay(4, "礼拜四");
    public static final WeekDay FRIDAY = new WeekDay(5, "礼拜五");
    public static final WeekDay SATURDAY = new WeekDay(6, "礼拜六");
    public static final WeekDay SUNDAY = new WeekDay(7, "礼拜天");
}

/**
 * 枚举类
 */
@Getter
enum WeekDayEnum {
    /**
     * 礼拜一
     */
    MONDAY(1, "礼拜一"),
    TUESDAY(2, "礼拜二"),
    WEDNESDAY(3, "礼拜三"),
    THURSDAY(4, "礼拜四"),
    FRIDAY(5, "礼拜五"),
    SATURDAY(6, "礼拜六"),
    SUNDAY(7, "礼拜天"),
    ;
    private final Integer code;
    private final String desc;

    WeekDayEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
}