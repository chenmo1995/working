package com.fudn.minimybatisplus.demo.mybatisplus.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcTemplate，简化jdbc操作
 *
 * @author bravo1988
 * @date 2021-05-29 17:24
 */
public class JdbcTemplate<T> {

    public List<T> queryForList(String sql, List<Object> params, RowMapper<T> rowMapper) throws SQLException {
        return query(sql, params, rowMapper);
    }

    public T queryForObject(String sql, List<Object> params, RowMapper<T> rowMapper) throws SQLException {
        List<T> result = query(sql, params, rowMapper);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<T> queryForList(String sql, List<Object> params, Class<T> clazz) throws Exception {
        return query(sql, params, clazz);
    }

    public T queryForObject(String sql, List<Object> params, Class<T> clazz) throws Exception {
        List<T> result = query(sql, params, clazz);
        return result.isEmpty() ? null : result.get(0);
    }

    public int update(String sql, List<Object> params) throws SQLException {
        // 1.获取Connection
        Connection conn = getConnection();

        // 2.传入sql模板、sql参数，得到PreparedStatement
        PreparedStatement ps = getPreparedStatement(sql, params, conn);

        // 3.执行更新（增删改）
        int affectedRows = ps.executeUpdate();

        // 4.释放资源
        closeConnection(conn, ps, null);

        return affectedRows;
    }

    // ************************* private methods **************************

    private List<T> query(String sql, List<Object> params, RowMapper<T> rowMapper) throws SQLException {
        // 外部传入rowMapper（手写规则）
        return baseQuery(sql, params, rowMapper);
    }

    private List<T> query(String sql, List<Object> params, Class<T> clazz) throws Exception {
        // 自己创建rowMapper（反射）后传入
        BeanHandler<T> beanHandler = new BeanHandler<>(clazz);
        return baseQuery(sql, params, beanHandler);
    }

    /**
     * 基础查询方法，必须传入Bean的映射规则
     *
     * @param sql
     * @param params
     * @param rowMapper
     * @return
     * @throws SQLException
     */
    private List<T> baseQuery(String sql, List<Object> params, RowMapper<T> rowMapper) throws SQLException {
        // TODO 参数非空校验

        // 1.获取Connection
        Connection conn = getConnection();

        // 2.传入sql模板、sql参数，得到PreparedStatement
        PreparedStatement ps = getPreparedStatement(sql, params, conn);

        // 3.执行查询
        ResultSet rs = ps.executeQuery();

        // 4.处理结果
        List<T> result = new ArrayList<>();
        while (rs.next()) {
            T obj = rowMapper.mapRow(rs);
            result.add(obj);
        }

        // 5.释放资源
        closeConnection(conn, ps, rs);
        return result;
    }

    /**
     * 内部类，实现了RowMapper接口，底层使用反射
     *
     * @param <R>
     */
    private static class BeanHandler<R> implements RowMapper<R> {
        // clazz表示最终封装的bean类型
        private Class<R> clazz;

        public BeanHandler(Class<R> clazz) {
            this.clazz = clazz;
        }

        @Override
        public R mapRow(ResultSet rs) {
            try {
                if (rs.next()) {
                    // 1.获取表数据
                    ResultSetMetaData metaData = rs.getMetaData();

                    // 2.反射创建bean
                    R bean = clazz.newInstance();

                    // 3.利用反射，把表数据设置到bean中
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        String name = metaData.getColumnName(i + 1);
                        Object value = rs.getObject(name);
                        Field field = clazz.getDeclaredField(name);
                        field.setAccessible(true);
                        field.set(bean, value);
                    }

                    // 4.返回bean
                    return bean;
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private PreparedStatement getPreparedStatement(String sql, List<Object> params, Connection conn) throws SQLException {
        // 1.传入sql模板，得到PreparedStatement
        PreparedStatement ps = conn.prepareStatement(sql);

        // 2.为sql模板设置参数
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        return ps;
    }

    private Connection getConnection() throws SQLException {
        // TODO 可以抽取配置到properties文件
        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }

    private void closeConnection(Connection conn, PreparedStatement preparedStatement, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (conn != null) {
            conn.close();
        }
    }

}
