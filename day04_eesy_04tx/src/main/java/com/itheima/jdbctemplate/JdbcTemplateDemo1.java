package com.itheima.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/eesy1");
        ds.setUsername("root");
        ds.setPassword("root");

        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(ds);
        jt.execute("insert into account(name, money)values('ccc', 1000)");
    }
}
