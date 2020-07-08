package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
    User findById(Integer userId);
    List<User> findByName(String username);
    List<User> findUserByVo(QueryVo vo);
    List<User> findUserByCondition(User user);

    List<User> findUserInIds(QueryVo vo);
}
