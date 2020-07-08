package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 */

//    <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
@Repository("accountDao1")
public class AccountDaoImpl2 implements IAccountDao {


    public  void saveAccount(){

        System.out.println("数据被保存了11111111111111111111");
    }
}
