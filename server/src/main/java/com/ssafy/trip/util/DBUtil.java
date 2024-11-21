package com.ssafy.trip.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/ssafytrip?serverTimezone=UTC";
    private final String DB_ID = "ssafy";
    private final String DB_PWD = "ssafy";

    private static DBUtil dbUtil = new DBUtil();

    private DBUtil() {
        try {
            Class.forName(DRIVER);
            System.out.println("드라이버 로딩 성공!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBUtil getDbUtil() {
        return dbUtil;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, DB_PWD, DB_ID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void close(AutoCloseable... closables) {
        for (AutoCloseable closable : closables) {
            try {
                if (closable != null) {
                    closable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}