package com.itheima.dao;


import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户的持久层
 */
public interface IAccountDao {
    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);

    Account findAccountByName(String accountName);
}
