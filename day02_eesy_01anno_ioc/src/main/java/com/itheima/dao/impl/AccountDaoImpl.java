package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 */

//    <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {


    public  void saveAccount(){

        System.out.println("数据被保存了");
    }
}
