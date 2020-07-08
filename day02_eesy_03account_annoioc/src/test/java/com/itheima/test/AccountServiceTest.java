package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用junit单元测试，测试我们的配置
 */

public class AccountServiceTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    IAccountService as = ac.getBean("accountService", IAccountService.class);



    @Test
    public void testFindAll() {
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("xiaoming");
        account.setMoney((float) 123456);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(4);
        account.setMoney((float) 123456111);
        account.setName("t132");
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(4);
    }
}
