package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<User> findAll();
    public User login(User user);
    public void addUser(User user);
    public void deleteUser(String id);

    User findUserById(String id);

    void updateUser(User user);

    void delSelectedUser(String[] uids);

    //分页查询(条件)
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
