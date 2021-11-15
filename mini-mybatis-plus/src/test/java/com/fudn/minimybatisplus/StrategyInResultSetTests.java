package com.fudn.minimybatisplus;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StrategyInResultSetTests {

    // 第二步：新增一个参数：RowMapper<T> handler，传入具体的封装策略
    public <T> List<T> query(String sql, Object[] params, RowMapper<T> handler) throws SQLException {
        // 1.获取连接
        Connection conn = getConnection();

        // 2.传入sql模板得到PreparedStatement
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 3.设置模板参数
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        // 4.执行语句
        ResultSet rs = preparedStatement.executeQuery();

        // 5.处理结果
        List<T> result = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs);
            T obj = handler.mapRow(rs);
            result.add(obj);
        }

        // 6.释放资源
        closeConnection(conn, preparedStatement, null);

        return result;
    }

    // 第三步：使用query方法时，传入封装的规则（策略模式）
    @Test
    public void testQuery() throws SQLException {
        String sql = "select * from t_user where id = ?";
        Object[] params = new Object[]{1}; // id=1
        // 直接传入匿名对象
        List<User> userList = query(sql, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) {
                User user = null;
                try {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setAge(rs.getInt("age"));
                    user.setName(rs.getString("name"));
                    user.setBirthday(rs.getDate("birthday"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return user;
            }
        });
        System.out.println(userList);
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/fudn_study_mp";
        String user = "root";
        String password = "fu1112111";
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

// 第一步：定义一个封装策略的接口
@FunctionalInterface
interface RowMapper<T> {
    /**
     * 将结果集转为指定的Bean
     *
     * @param resultSet
     * @return
     */
    T mapRow(ResultSet resultSet);
}

@Data
class User {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
}

