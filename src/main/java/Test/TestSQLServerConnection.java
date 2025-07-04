package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLServerConnection {
    public static void main(String[] args) {
        // 请根据实际情况修改下面的连接字符串、用户名、密码
        String url = "jdbc:sqlserver://localhost:1433;database=test;encrypt=true;trustServerCertificate=true";

        String user = "sa";
        String password = "root";

        Connection conn = null;
        try {
            // 尝试建立连接
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
            e.printStackTrace();
        } finally {
            // 关闭连接，释放资源
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // 忽略关闭异常
                }
            }
        }
    }
}
