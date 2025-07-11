package Test;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;database=test;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "root";
        String sqlFolderPath = "E:/JAVA-project/Temp/src/main/resources/struct"; // ⚠️ 修改为你的 SQL 文件夹路径

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("数据库连接成功！");
            File folder = new File(sqlFolderPath);
            File[] sqlFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".sql"));

            if (sqlFiles == null || sqlFiles.length == 0) {
                System.out.println("未找到任何 .sql 文件！");
                return;
            }

            for (File sqlFile : sqlFiles) {
                System.out.println("正在执行文件: " + sqlFile.getName());
                StringBuilder sqlBuilder = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(new FileReader(sqlFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.startsWith("--") || line.isEmpty()) {
                            continue;
                        }
                        sqlBuilder.append(line).append("\n");
                    }
                }

                // 按 GO 分割 SQL 语句块（忽略大小写，GO 必须单独一行或带空格）
                String[] sqlBatches = sqlBuilder.toString().split("(?i)^\\s*GO\\s*$", -1);

                try (Statement stmt = conn.createStatement()) {
                    for (String batch : sqlBatches) {
                        String trimmed = batch.trim();
                        if (!trimmed.isEmpty()) {
                            System.out.println("执行 SQL: \n" + trimmed);
                            stmt.execute(trimmed);
                        }
                    }
                }

                System.out.println("文件执行完成: " + sqlFile.getName());
            }

            System.out.println("所有 SQL 脚本执行完成！");
        } catch (Exception e) {
            System.err.println("执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}

