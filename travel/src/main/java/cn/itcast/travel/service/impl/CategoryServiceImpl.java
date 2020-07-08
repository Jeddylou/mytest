package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();


    @Override
    public List<Category> findAll() {
        //从redis中查询
        Jedis jedis = JedisUtil.getJedis();
//        Set<String> category = jedis.zrange("category", 0, -1);
        //查询sortedset中的分数和值
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs = null;
        //判断查询的集合是否为空
        if(category == null || category.size() == 0){

            System.out.println("case1");
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }


        }else{
            cs = new ArrayList<Category>();
            for (Tuple tuple : category) {
                Category category1 = new Category();
                category1.setCname(tuple.getElement());
                category1.setCid((int) tuple.getScore());
                cs.add(category1);

            }
        }

        return cs;
    }
}
