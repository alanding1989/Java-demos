package com.feiyangedu.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSelect {

  private static final String JDBC_URL =
      "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
  private static final String JDBC_USER = "root";
  private static final String JDBC_PASSWORD = "193728456";

  public static void main(String[] args) throws Exception {
    List<Student> students = getAllStudents();
    for (Student student : students) {
      System.out.println(student);
    }
    for (int i = 1; i <= 4; i++) {
      List<Student> list = getStudentsOfClass(i);
      System.out.println("Students of class " + i + ":");
      for (Student student : list) {
        System.out.println(student);
      }
    }
  }

  private static List<Student> getAllStudents() throws SQLException {
    try (Connection conn = getConnection()) {
      try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM students")) {
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
    }
  }

  private static List<Student> getStudentsOfClass(long theClassId) throws SQLException {
    try (Connection conn = getConnection()) {
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
    }
  }

  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
  }
}
