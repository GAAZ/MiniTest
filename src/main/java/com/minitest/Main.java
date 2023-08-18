package com.minitest;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author wsw
 * @description 测试数据库连接
 * @CreatTime 2023/8/17 9:42
 */
public class Main {
    public static void main(String[] args) {


        System.out.println("Test Connection Database...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input DB IP:");
        String ip = scanner.nextLine();
        System.out.println("Please input DB user:");
        String user = scanner.nextLine();
        System.out.println("Please input DB password:");
        String password = scanner.nextLine();
        System.out.println("Please input DB serverName:");
        String serverName = scanner.nextLine();
        System.out.println("Please input DB port:");
        String port = scanner.nextLine();
        Connection connection = null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String dbUrl = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + serverName;
            // 1.加载驱动
            String driver = "oracle.jdbc.driver.OracleDriver";
            System.out.println("Load Driver: "+ driver);
            System.out.println("Local IP: "+ localHost.getHostAddress() + ", DB Url:" + dbUrl );
            Class.forName(driver);
            // 2.获取连接
            connection = DriverManager.getConnection(dbUrl, user, password);
            DatabaseMetaData metaData = connection.getMetaData();
            // 3.获取数据库版本信息
            System.out.println("DB connection successful!");
            System.out.println("DB version:"+ metaData.getDatabaseProductVersion());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Close DB connection failed!");
                    e.printStackTrace();
                }
                System.out.println("Close DB connection successful!");
            }
        }
    }
}