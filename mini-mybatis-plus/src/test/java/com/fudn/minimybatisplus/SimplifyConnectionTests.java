package com.fudn.minimybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.time.LocalDate;

@SpringBootTest
class SimplifyConnectionTests {

    /**
     * 不论是query还是update，都有获取Connection和关闭Connection的操作
     * 优化第一步：抽取getConnection()和closeConnection()
     */
    @Test
    public void testQuery() throws SQLException {
        // 1.获取连接
        Connection conn = this.getConnection();

        // 2.创建sql模板
        String sql = "select * from t_user where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 3.设置模板参数 id=1
        preparedStatement.setInt(1, 1);

        // 4.执行语句
        ResultSet rs = preparedStatement.executeQuery();

        // 5.处理结果
        while (rs.next()) {
            System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
                    + rs.getObject(3) + "\t" + rs.getObject(4));
        }

        // 6.释放资源
        this.closeConnection(conn, preparedStatement, rs);
    }

    @Test
    public void testUpdate() throws SQLException {
        // 1.获取连接
        Connection conn = this.getConnection();

        // 2.创建sql模板
        String sql = "insert into t_user(name, age, birthday) values(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 3.设置模板参数
        preparedStatement.setString(1, "bravo1988");
        preparedStatement.setInt(2, 18);
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

        // 4.执行语句
        preparedStatement.executeUpdate();

        // 5.释放资源
        this.closeConnection(conn, preparedStatement, null);
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
