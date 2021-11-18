package com.fudn.minimybatisplus.demo.mybatisplus;

import com.fudn.minimybatisplus.demo.mybatisplus.annotations.TableName;
import com.fudn.minimybatisplus.demo.mybatisplus.core.JdbcTemplate;
import com.fudn.minimybatisplus.demo.mybatisplus.query.QueryWrapper;
import com.fudn.minimybatisplus.demo.mybatisplus.query.SqlParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Mapper基类
 *
 * @author bravo1988
 * @date 2021-05-29 18:02
 */
public abstract class AbstractBaseMapper<T> {

    private static Logger logger = LoggerFactory.getLogger(AbstractBaseMapper.class);

    private JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<T>();

    private Class<T> beanClass;

    private final String TABLE_NAME;

    private static final String DEFAULT_LOGICAL_TYPE = " and ";

    public AbstractBaseMapper() {
        // DO对象的Class
        beanClass = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        // DO对应的表名 TODO 非空判断及默认处理
        TABLE_NAME = beanClass.getAnnotation(TableName.class).value();
    }

    public T select(QueryWrapper<T> queryWrapper) {
        List<T> list = this.list(queryWrapper);
        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    public List<T> list(QueryWrapper<T> queryWrapper) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE ");

        List<Object> paramList = new ArrayList<>();
        Map<String, SqlParam> conditionMap = queryWrapper.build();
        conditionMap.forEach((operator, param) -> {
            sqlBuilder.append(param.getColumnName()).append(operator).append("?").append(DEFAULT_LOGICAL_TYPE);
            paramList.add(param.getValue());
        });

        // 删除最后一个 and
        String sql = sqlBuilder.replace(sqlBuilder.length() - DEFAULT_LOGICAL_TYPE.length(), sqlBuilder.length(), ";").toString();

        try {
            logger.info("sql: {}", sql);
            logger.info("params: {}", paramList);
            return jdbcTemplate.queryForList(sql, paramList, beanClass);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("query failed", e);
        }

        return Collections.emptyList();
    }

    public int insert(T bean) {
        // 得到DO对象的所有字段
        Field[] declaredFields = beanClass.getDeclaredFields();

        // 拼接sql语句，表名来自DO的TableName注解value
        StringBuilder sqlBuilder = new StringBuilder()
                .append("INSERT INTO ")
                .append(TABLE_NAME)
                .append(" VALUES(");
        for (int i = 0; i < declaredFields.length; i++) {
            sqlBuilder.append("?");
            if (i < declaredFields.length - 1) {
                sqlBuilder.append(",");
            }
        }
        sqlBuilder.append(")");

        // 收集sql参数
        ArrayList<Object> paramList = new ArrayList<>();
        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object o = declaredField.get(bean);
                paramList.add(o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int affectedRows = 0;
        try {
            logger.info("sql: {}", sqlBuilder.toString());
            logger.info("params: {}", paramList);
            affectedRows = jdbcTemplate.update(sqlBuilder.toString(), paramList);
            logger.info("insert success, affectedRows: {}", affectedRows);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("insert failed", e);
        }

        return 0;
    }

    public int updateSelective(T bean, QueryWrapper<T> queryWrapper) {
        // 得到DO对象的所有字段
        Field[] declaredFields = beanClass.getDeclaredFields();

        // 拼接sql语句，表名来自DO的TableName注解value
        StringBuilder sqlSetBuilder = new StringBuilder()
                .append("UPDATE ")
                .append(TABLE_NAME)
                .append(" SET ");

        List<Object> paramList = new ArrayList<>();

        // 先拼接要SET的字段占位符 SET name=?, age=?
        try {
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                declaredField.setAccessible(true);
                Object fieldValue = declaredField.get(bean);
                if (fieldValue != null) {
                    sqlSetBuilder.append(declaredField.getName()).append(" = ").append("?").append(", ");
                    paramList.add(fieldValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 删除最后一个 ,
        sqlSetBuilder = sqlSetBuilder.delete(sqlSetBuilder.length() - 2, sqlSetBuilder.length());

        // 再拼接WHERE条件占位符
        StringBuilder sqlWhereBuilder = new StringBuilder(" WHERE ");
        Map<String, SqlParam> conditionMap = queryWrapper.build();
        for (Map.Entry<String, SqlParam> stringSqlParamEntry : conditionMap.entrySet()) {
            String operator = stringSqlParamEntry.getKey();
            SqlParam param = stringSqlParamEntry.getValue();
            sqlWhereBuilder.append(param.getColumnName()).append(operator).append("?").append(DEFAULT_LOGICAL_TYPE);
            paramList.add(param.getValue());
        }
        // 删除最后一个 and
        sqlWhereBuilder = sqlWhereBuilder.replace(sqlWhereBuilder.length() - DEFAULT_LOGICAL_TYPE.length(), sqlWhereBuilder.length(), ";");

        String sql = sqlSetBuilder.append(sqlWhereBuilder).toString();

        int affectedRows = 0;
        try {
            logger.info("sql: {}", sqlSetBuilder.toString());
            logger.info("params: {}", paramList);
            affectedRows = jdbcTemplate.update(sql, paramList);
            logger.info("update success, affectedRows: {}", affectedRows);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("update failed", e);
        }

        return 0;
    }

}
