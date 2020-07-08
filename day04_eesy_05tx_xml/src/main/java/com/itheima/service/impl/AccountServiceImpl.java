package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public Account findAccountById(Integer accountId) {
       return accountDao.findAccountById(accountId);
    }




    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("开始执行");
            Account source = accountDao.findAccountByName(sourceName);
            //2.根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //3.转出账户捡钱
            source.setMoney(source.getMoney() - money);
            //4.转入账户价钱
            target.setMoney(target.getMoney() + money);
            //5.跟新
            accountDao.updateAccount(source);
            //int i = 1 / 0;
            accountDao.updateAccount(target);
            //提交事务
            //返回结果


    }
}
