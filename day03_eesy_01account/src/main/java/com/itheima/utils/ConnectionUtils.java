package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 链接的工具类，从数据源中获取一个链接，实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的链接
     * @return
     */
    public Connection getThreadConnection() {
        try {
            Connection conn = tl.get();
            if (conn == null) {

                conn = dataSource.getConnection();
                tl.set(conn);

            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void removeConnection(){
        tl.remove();
    }
}
