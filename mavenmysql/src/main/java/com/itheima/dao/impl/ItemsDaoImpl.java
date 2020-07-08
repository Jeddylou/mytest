package com.itheima.dao.impl;

import com.itheima.dao.ItemsDao;
import com.itheima.domain.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements ItemsDao {
    public List<Items> findAll() throws Exception {
        List<Items> list = new ArrayList<Items>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;

       try{
           Class.forName("com.mysql.jdbc.Driver");
           //先获取connection对象
            conn =  DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");

           pst = conn.prepareCall("select * from user");
           //直接执行
           rst = pst.executeQuery();
           //把数据库结果集转成Java集合

           while(rst.next()){
               Items items = new Items();
               items.setId(rst.getInt("id"));
               items.setName(rst.getString("name"));
               list.add(items);
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           rst.close();
           pst.close();
           conn.close();
       }

        return list;

    }
}
