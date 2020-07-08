package com.itheima.cglib;

/**
 * 模拟消费者
 */

import com.itheima.proxy.IProducer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 特点：字节码随用随创建。随用随加载
 * 作用：不修改源码，对原有方法加强
 */
public class Client {

    public static void main(String[] args) {
        final Producer producer = new Producer();

        Enhancer.create();

    }
}
