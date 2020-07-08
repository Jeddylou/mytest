package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    @Before
    public void init() throws IOException {
        in = org.apache.ibatis.io.Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }


    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("--------每个角色的信息--------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }



}
