package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username, String password);
    public void add(User user);
    void delete(int i);
    User findById(int i);
    void update(User user);
    //查询总记录数
    int findTotalCount(Map<String, String[]> condition);
    //分页查询每页的记录
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
