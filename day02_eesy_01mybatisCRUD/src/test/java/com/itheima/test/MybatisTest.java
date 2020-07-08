package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = org.apache.ibatis.io.Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }



    @Test
    public void testFindAll() throws IOException {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUsername("mybatis saveuser");
        user.setAddress("北京市");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存之前"+user);
        userDao.saveUser(user);
        System.out.println("保存之后"+user);

    }

    @Test
    public void testUpdate()  {
        User user = new User();
        user.setId(50);
        user.setUsername("mybatis updateuser");
        user.setAddress("北京市123");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);

    }

    @Test
    public void testDelete()  {
        userDao.deleteUser(50);


    }

    @Test
    public void testFindOne()  {
        User user = userDao.findById(48);
        System.out.println(user);


    }

    @Test
    public void testFinByName()  {
//        List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindTotal()  {
        int count = userDao.findTotal();
        System.out.println(count);

    }

    @Test
    public void testFindByVo()  {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> list = userDao.findUserByVo(queryVo);
//        List<User> users = userDao.findByName("王");
        for (User user1 : list) {
            System.out.println(user1);

        }

    }
}
