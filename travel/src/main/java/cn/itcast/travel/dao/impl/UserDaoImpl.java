package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import com.alibaba.druid.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByUsername(String username) {
        User user = null;
        try{
            //定义sql
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){

        }

        return user;
    }

    @Override
    public void save(User user) {

        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, status, code) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());

    }

    @Override
    public User findByCode(String code) {
        //根据激活码查询用户对象
        String sql = "select * from tab_user where code = ?";
        User user = null;
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e){

        }
        return user;



    }

    @Override
    public void updateStatus(User user) {
    //修改用户状态

        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql, user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        //根据密码查询
        User user = null;
        try{
            //定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception e){

        }

        return user;
    }
}
