package com.fudn.minimybatisplus.demo.mybatisplus.query;


import com.fudn.minimybatisplus.demo.mybatisplus.utils.ConditionFunction;
import com.fudn.minimybatisplus.demo.mybatisplus.utils.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟MyBatis-Plus的LambdaQueryWrapper（思路完全不同，仅仅是形似）
 *
 * @author bravo1988
 * @date 2021-05-29 19:41
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
