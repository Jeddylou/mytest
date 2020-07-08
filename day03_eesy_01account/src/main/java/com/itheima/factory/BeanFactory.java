package com.itheima.factory;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 用于创建Service代理对象的工厂
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public IAccountService getAccountService(){
        IAccountService i1 = (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object rtValue = null;
                try{
                    //开启事务
                    txManager.beginTransaction();
                    //执行操作
                    rtValue = method.invoke(accountService, args);
                    //提交事务
                    txManager.commit();
                    //返回结果
                    return rtValue;

                }catch (Exception e){
                    //回滚操作
                    txManager.rollback();
                    throw new RuntimeException(e);
                }finally {
                    //释放连接
                    txManager.release();

                }
            }
        });
        return i1;
    }
}
