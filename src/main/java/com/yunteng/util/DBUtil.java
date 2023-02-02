package com.yunteng.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBUtil {
    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> threadLocal
                = new ThreadLocal<>();
    static{
        Properties properties = new Properties();
        try {
            properties.load(
                    new FileInputStream("src/main/java/com/yunteng/util/druid.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws Exception{
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
    public static void freeConnection() throws Exception{
        Connection connection = threadLocal.get();
        if(connection!= null){
            threadLocal.remove();
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
