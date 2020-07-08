package com.itheima.factory;

//模拟一个工厂类，无法修改源码来提供默认构造函数

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

public class StaticFactory {

    public static IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
