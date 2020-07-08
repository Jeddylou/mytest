package cn.itcast.jedis.test;


import cn.itcast.jedis.util.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

//jedis测试类
public class JedisTest {

    @Test
    public void test1(){
        Jedis jedis = new Jedis();

        jedis.set("username", "zhangsan");

        String username = jedis.get("username");
        System.out.println(username);

        jedis.setex("activecode", 20, "hehe");

        jedis.close();

    }

    @Test
    public void test2(){
        Jedis jedis = new Jedis();
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "male");

        String name = jedis.hget("user", "name");
        System.out.println(name);

        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            String s = user.get(key);
            System.out.println(key + ":" + s);

        }


        jedis.close();

    }

    @Test
    public void test3(){
        Jedis jedis = new Jedis();

        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", "a", "b", "c");

        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        jedis.close();

    }

    @Test
    public void test4(){
        //1.创建连接池对象

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        Jedis jedis = jedisPool.getResource();

        jedis.set("hehe", "haha");



        jedis.close();

    }

    @Test
    public void test5(){
        //1.创建连接池对象

        Jedis jedis = JedisUtils.getJedis();
        jedis.set("hello", "world");

        jedis.close();

    }



}
