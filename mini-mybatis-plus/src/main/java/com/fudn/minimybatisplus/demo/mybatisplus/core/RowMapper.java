package com.fudn.minimybatisplus.demo.mybatisplus.core;

import java.sql.ResultSet;

/**
 * 结果集映射器
 *
 * @author bravo1988
 * @date 2021-05-29 17:28
 */
@FunctionalInterface
public interface RowMapper<T> {
    /**
     * 将结果集转为指定的Bean
     *
     * @param resultSet
     * @return
     */
    T mapRow(ResultSet resultSet);
}
