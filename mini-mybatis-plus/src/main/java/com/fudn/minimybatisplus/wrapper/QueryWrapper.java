package com.fudn.minimybatisplus.wrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟MyBatis-Plus的LambdaQueryWrapper（思路完全不同，仅仅是形似）
 * 主要思路是：把所有查询条件封装到Map中，key是操作符，
 * 比如=、like、>或<，value是SqlParam对象，包括columnName和columnValue。
 *
 * @author fdn
 * @since 2021-11-16 23:39
 */
public class QueryWrapper<T> {
    // conditionMap，收集查询条件
    // {
    //    " LIKE ": {
    //        "name": "bravo1988"
    //    },
    //    " = ": {
    //        "age": 18
    //    }
    // }
    private final Map<String, SqlParam> conditionMap = new HashMap<>();

    // 操作符类型，比如 name like 'bravo' 中的 LIKE
    private static final String OPERATOR_EQ = " = ";
    private static final String OPERATOR_GT = " > ";
    private static final String OPERATOR_LT = " < ";
    private static final String OPERATOR_LIKE = " LIKE ";

    public QueryWrapper<T> eq(ConditionFunction<T, ?> fn, Object value) {
        String columnName = Reflections.fnToColumnName(fn);
        conditionMap.put(OPERATOR_EQ, new SqlParam(columnName, value));
        return this;
    }

    public QueryWrapper<T> gt(ConditionFunction<T, ?> fn, Object value) {
        String columnName = Reflections.fnToColumnName(fn);
        conditionMap.put(OPERATOR_GT, new SqlParam(columnName, value));
        return this;
    }

    public QueryWrapper<T> lt(ConditionFunction<T, ?> fn, Object value) {
        String columnName = Reflections.fnToColumnName(fn);
        conditionMap.put(OPERATOR_LT, new SqlParam(columnName, value));
        return this;
    }

    public QueryWrapper<T> like(ConditionFunction<T, ?> fn, Object value) {
        String columnName = Reflections.fnToColumnName(fn);
        conditionMap.put(OPERATOR_LIKE, new SqlParam(columnName, "%" + value + "%"));
        return this;
    }

    public Map<String, SqlParam> build() {
        return conditionMap;
    }
}
