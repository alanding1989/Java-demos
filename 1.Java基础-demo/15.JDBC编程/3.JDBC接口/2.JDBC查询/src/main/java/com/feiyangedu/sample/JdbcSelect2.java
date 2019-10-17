package com.feiyangedu.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSelect2 {

  private static final String JDBC_URL =
      "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
  private static final String JDBC_USER = "root";
  private static final String JDBC_PASSWORD = "193728456";

  public static void main(String[] args) throws Exception {
    for (int i = 1; i <= 4; i++) {
      System.out.println("Students of class " + i + ": " + getNumOfStudents(i));
    }
  }

  private static long getNumOfStudents(long classId) throws SQLException {
    try (Connection conn = getConnection()) {
      try (PreparedStatement ps =
          conn.prepareStatement("SELECT COUNT(*) num FROM students WHERE class_id=?")) {
        ps.setObject(1, classId);
        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            // long num = rs.getLong(1); 注意:如果使用索引，index从1开始
            return rs.getLong("num");
          }
          throw new RuntimeException("Empty result set.");
        }
      }
    }
  }

  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
  }
}
