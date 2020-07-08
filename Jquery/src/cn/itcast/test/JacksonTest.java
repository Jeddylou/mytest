package cn.itcast.test;

import cn.itcast.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JacksonTest {

    @Test
    public void test1() throws IOException {
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");


        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(p);
        //System.out.println(json);


//        mapper.writeValue(new File("d://a.txt"), p);//数据写道文件中

        mapper.writeValue(new FileWriter("d://a.txt"), p);//字节流



    }

    @Test
    public void test2() throws IOException {
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(p);

        System.out.println(json);


    }
    @Test
    public void test3() throws IOException {
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        Person p1 = new Person();
        p1.setName("张4");
        p1.setAge(23);
        p1.setGender("男");
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("张5");
        p1.setAge(23);
        p2.setGender("男");
        p2.setBirthday(new Date());

        List<Person> ps = new ArrayList<Person>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(ps);

        System.out.println(json);


    }

    @Test
    public void test4() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("gender", "男");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(map);

        System.out.println(json);


    }

    @Test
    public void test5() throws IOException {
        //初始化字符串
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";

        ObjectMapper mapper = new ObjectMapper();

        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);


    }


    }

