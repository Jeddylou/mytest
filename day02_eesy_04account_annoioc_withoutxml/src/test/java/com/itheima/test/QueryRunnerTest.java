package com.itheima.test;

import config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试queryRunner是否单例
 */
public class QueryRunnerTest {

    @Test
    public void testQueryRunner() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //获取queryRunner对象
        QueryRunner runner = ac.getBean("runner", QueryRunner.class);
        QueryRunner runner1 = ac.getBean("runner", QueryRunner.class);
        System.out.println(runner == runner1);
    }
}
