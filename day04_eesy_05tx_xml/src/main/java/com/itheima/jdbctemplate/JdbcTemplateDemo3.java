package com.itheima.jdbctemplate;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的CRUD
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
//        jt.execute("insert into account(name, money)values('eee', 2000)");
//        jt.update("insert into account(name, money)values(?,?)","eee", 3333f);
  //      jt.update("update account set name = ?,money = ? where id = ?", "test", 4567, 7);
 //       jt.update("delete from account where id = ?", 8);
//
//        List<Account> accountList = jt.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),1);
////        for (Account account : accountList) {
//////            System.out.println(account);
//////        }
//        System.out.println(accountList.isEmpty()?"没有内容":accountList.get(0));

        Long count = jt.queryForObject("select count(*) from account where money > ?",Long.class,1000f);
        System.out.println(count);
    }
}

////定义Acoount的封装策略
//class AccountRowMapper implements RowMapper<Account>{
//
//    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Account account = new Account();
//        account.setId(rs.getInt("id"));
//        account.setName(rs.getString("name"));
//        account.setMoney(rs.getFloat("money"));
//        return account;
//    }
//}