package mysql_test.sql_test_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.UUID;

public class InsertRandomData {

    private static final String URL = "jdbc:mysql://localhost:3307/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            insertTable1(conn, 3000);
            insertTable2(conn, 70000);

            conn.commit();
            System.out.println("数据插入完成！");
        }
    }

    /** 插入 t_table1 */
    private static void insertTable1(Connection conn, int count) throws Exception {
        String sql = "INSERT INTO t_table1(a, b, c) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                ps.setString(1, randomStr(10));
                ps.setString(2, randomStr(10));
                ps.setString(3, "C" + random.nextInt(1000));

                ps.addBatch();

                if (i % 500 == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
            System.out.println("t_table1 插入完成: " + count + " 条");
        }
    }

    /** 插入 t_table2 */
    private static void insertTable2(Connection conn, int count) throws Exception {
        String sql = "INSERT INTO t_table2(a, x, y) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                ps.setString(1, randomStr(10));
                ps.setString(2, UUID.randomUUID().toString().substring(0, 12));
                ps.setString(3, "Y" + random.nextInt(1000));

                ps.addBatch();

                if (i % 500 == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
            System.out.println("t_table2 插入完成: " + count + " 条");
        }
    }

    /** 随机字符串生成 */
    private static String randomStr(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(r.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
