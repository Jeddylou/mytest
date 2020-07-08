package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl_OLD implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {

        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //提交事务
            txManager.commit();
            //返回结果
            return accounts;

        }catch (Exception e){
            //回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            txManager.release();

        }
    }

    public Account findAccountById(Integer accountId) {
        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
            Account account = accountDao.findAccountById(accountId);
            //提交事务
            txManager.commit();
            //返回结果
            return account;

        }catch (Exception e){
            //回滚操作
            txManager.rollback();
            throw new RuntimeException(e);

        }finally {
            //释放连接
            txManager.release();

        }
    }

    public void saveAccount(Account account) {
        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
           accountDao.saveAccount(account);
            //提交事务
            txManager.commit();
            //返回结果

        }catch (Exception e){
            //回滚操作
            txManager.rollback();

        }finally {
            //释放连接
            txManager.release();

        }
    }

    public void updateAccount(Account account) {
        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
           accountDao.updateAccount(account);
            //提交事务
            txManager.commit();
            //返回结果

        }catch (Exception e){
            //回滚操作
            txManager.rollback();

        }finally {
            //释放连接
            txManager.release();

        }
    }

    public void deleteAccount(Integer accountId) {
        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
            accountDao.deleteAccount(accountId);
            //提交事务
            txManager.commit();
            //返回结果


        }catch (Exception e){
            //回滚操作
            txManager.rollback();

        }finally {
            //释放连接
            txManager.release();

        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
            //1.根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            //2.根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //3.转出账户捡钱
            source.setMoney(source.getMoney() - money);
            //4.转入账户价钱
            target.setMoney(target.getMoney() + money);
            //5.跟新
            accountDao.updateAccount(source);
            int i = 1 / 0;
            accountDao.updateAccount(target);
            //提交事务
            txManager.commit();
            //返回结果

        }catch (Exception e){
            //回滚操作
            txManager.rollback();
            e.printStackTrace();

        }finally {
            //释放连接
            txManager.release();

        }



    }
}
