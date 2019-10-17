package com.feiyangedu.sample;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class JdbcSelect {
    private static final String JDBC_URL =
        "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "193728456";

    public static void main(String[] args) throws Exception {
        HikariDataSource dataSource = createDataSource();
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            final int classId = i;
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Student> list = getStudentsOfClass(dataSource, classId);
                System.out.println("Students of class " + classId + ":");
                for (Student student : list) {
                    System.out.println(student);
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        // 关闭DataSource:
        dataSource.close();
    }

    private static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        return new HikariDataSource(config);
    }

    private static List<Student> getStudentsOfClass(DataSource dataSource, long theClassId) {
        try (Connection conn = dataSource.getConnection()) {
            System.err.println("Using connection: " + conn);
            try (PreparedStatement ps =
                conn.prepareStatement("SELECT * FROM students WHERE class_id=?")) {
                ps.setObject(1, theClassId);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Student> list = new ArrayList<>();
                    while (rs.next()) {
                        // long id = rs.getLong(1); 注意:如果使用索引，index从1开始
                        long id = rs.getLong("id");
                        long classId = rs.getLong("class_id");
                        String name = rs.getString("name");
                        String gender = rs.getString("gender");
                        Student std = new Student(id, classId, name, gender);
                        list.add(std);
                    }
                    return list;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
