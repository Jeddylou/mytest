package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());




    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;

    }


    @Override
    public void add(User user) {
        String sql = "insert into user values(null, ?,?,?,?,?,?,null,null)";
        template.update(sql, user.getName(),user.getGender(),user.getAge(), user.getAddress(), user.getQq(),user.getEmail());

    }

    public User findUserByUsernameAndPassword(String username, String password){
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        User user =  template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;

    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql, user.getName(),user.getGender(),user.getAge(), user.getAddress(), user.getQq(),user.getEmail(), user.getId());
    }

//    @Override
////    public int findTotalCount(Map<String, String[]> condition) {
////        //初始化的
////        String sql = "select count(*) from user where 2 = 2 ";
////        StringBuilder sb = new StringBuilder(sql);
////        Set<String> keySet = condition.keySet();
////
////        //定义参数集合
////        List<Object> params = new ArrayList<Object>();
////        for (String key : keySet) {
////            if("currentPage".equals(key) || "rows".equals(key)){
////                continue;
////            }
////            String value = condition.get(key)[0];
////            if(value != null && !"".equals(value))
////            {
//// //               sb.append(" and "+key+" like ? ");
////                sb.append(" and "+key+" like ? ");
////                params.add("%"+value+"%");
////             }
////
////        }
////        System.out.println(sb.toString());
////        System.out.println(params);
////
////        return template.queryForObject(sql, Integer.class, params.toArray());
////    }
public int findTotalCount(Map<String, String[]> condition) {
    //1.定义模板初始化sql
    String sql = "select count(*) from user where 1 = 1 ";
    StringBuilder sb = new StringBuilder(sql);
    //2.遍历map
    Set<String> keySet = condition.keySet();
    //定义参数的集合
    List<Object> params = new ArrayList<Object>();
    for (String key : keySet) {

        //排除分页条件参数
        if("currentPage".equals(key) || "rows".equals(key)){
            continue;
        }

        //获取value
        String value = condition.get(key)[0];
        //判断value是否有值
        if(value != null && !"".equals(value)){
            //有值
            sb.append(" and "+key+" like ? ");
            params.add("%"+value+"%");//？条件的值
        }
    }
//    System.out.println(sb.toString());
//    System.out.println(params);

    return template.queryForObject(sb.toString(),Integer.class,params.toArray());
}


    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询

        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);

        sql = sb.toString();
//        System.out.println(sb.toString());
//        System.out.println(params);

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}
