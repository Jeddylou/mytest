package com.itheima.test;

import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountServiceTest {

    @Test
    public void testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        List<Account> accounts = as.findAllAccount();


    }

    @Test
    public void testFindOne(){

    }

    @Test
    public void testSave(){

    }

    @Test
    public void testUpdate(){

    }

    @Test
    public void testDelete(){

    }
}
