package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class BatchInsertSampleEntity {
    private static final int TOTAL_ROWS = 1_000_000;
    private static final int BATCH_SIZE = 1000;

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;database=test;encrypt=true;trustServerCertificate=true";  // 修改成你的数据库名和地址
        String user = "sa";  // 修改成你的用户名
        String password = "root";  // 修改成你的密码

        String sql = "INSERT INTO SampleEntity (id, biz_code, name, category_id, status, amount, quantity, start_date, end_date, description, created_by, created_at, updated_by, updated_at, remark, ext1, ext2, ext3, ext4) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, GETDATE(), ?, ?, ?, ?, ?)";

        Random random = new Random();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);  // 关闭自动提交，提高批量插入性能

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 1; i <= TOTAL_ROWS; i++) {
                    ps.setString(1, UUID.randomUUID().toString());  // id，UUID字符串
                    ps.setString(2, String.format("CODE%06d", i));  // biz_code
                    ps.setString(3, "测试数据" + i);                 // name
                    ps.setInt(4, random.nextInt(10) + 1);           // category_id 1-10
                    ps.setInt(5, random.nextInt(2));                 // status 0 or 1
                    ps.setBigDecimal(6, BigDecimal.valueOf(100 + (10000 - 100) * random.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP)); // amount
                    ps.setInt(7, random.nextInt(100) + 1);          // quantity
                    ps.setDate(8, java.sql.Date.valueOf(LocalDate.of(2023, 1, 1).plusDays(random.nextInt(365)))); // start_date
                    ps.setDate(9, java.sql.Date.valueOf(LocalDate.of(2023, 1, 1).plusDays(random.nextInt(365) + 30))); // end_date
                    ps.setString(10, "描述" + i);                    // description
                    ps.setString(11, "admin");                       // created_by
                    ps.setString(12, "admin");                       // updated_by
                    ps.setString(13, "备注" + i);                    // remark
                    ps.setString(14, "自定义A" + i);                 // ext1
                    ps.setString(15, "自定义B" + i);                 // ext2
                    ps.setString(16, "自定义C" + i);                 // ext3
                    ps.setString(17, "自定义D" + i);                 // ext4

                    ps.addBatch();

                    if (i % BATCH_SIZE == 0) {
                        ps.executeBatch();
                        conn.commit();
                        System.out.println("已插入 " + i + " 条数据");
                    }
                }

                // 处理剩余未提交的
                ps.executeBatch();
                conn.commit();

                System.out.println("全部插入完成！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
