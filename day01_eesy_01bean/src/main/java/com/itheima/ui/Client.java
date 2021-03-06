package com.itheima.ui;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    //获取spring容器的ioc核心容器，并根据id获取对象

    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

        as.saveAccount();

        //手动关闭
        ((ClassPathXmlApplicationContext) ac).close();


    }
}
