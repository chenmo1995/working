package com.fudn.minimybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.time.LocalDate;

@SpringBootTest
class BaseJdbcTests {

    /**
     * CREATE TABLE `t_user` (
     *   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
     *   `name` varchar(255) DEFAULT '' COMMENT '姓名',
     *   `age` tinyint(3) unsigned DEFAULT '0' COMMENT '年龄',
     *   `birthday` date DEFAULT NULL COMMENT '生日',
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     * @throws SQLException
     */
    @Test
    void testQuery() throws SQLException {
        // 1.注册驱动（已经过时，现在不必注册驱动，DriverManager被加载时会自动注册）
//        Class.forName("com.mysql.jdbc.Driver");

        // 2.建立连接
        String url = "jdbc:mysql://localhost:3306/fudn_study_mp";
        String user = "root";
        String password = "fu1112111";
        Connection conn = DriverManager.getConnection(url, user, password);

        // 3.创建sql模板
        String sql = "select * from t_user where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 4.设置模板参数
        preparedStatement.setInt(1, 1);

        // 5.执行语句
        ResultSet rs = preparedStatement.executeQuery();

        // 6.处理结果
        while (rs.next()) {
            System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
                    + rs.getObject(3) + "\t" + rs.getObject(4));
        }

        // 7.释放资源
        rs.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        // 1.注册驱动（已经过时，现在不必注册驱动，DriverManager被加载时会自动注册）
//        Class.forName("com.mysql.jdbc.Driver");

        // 2.建立连接
        String url = "jdbc:mysql://localhost:3306/fudn_study_mp";
        String user = "root";
        String password = "fu1112111";
        Connection conn = DriverManager.getConnection(url, user, password);

        // 3.创建sql模板
        String sql = "insert into t_user(name, age, birthday) values(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 4.设置模板参数
        preparedStatement.setString(1, "bravo1988");
        preparedStatement.setInt(2, 18);
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

        // 5.执行语句
        preparedStatement.executeUpdate();

        // 6.释放资源
        preparedStatement.close();
        conn.close();
    }

}
