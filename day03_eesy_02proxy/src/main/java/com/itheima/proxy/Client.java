package com.itheima.proxy;

/**
 * 模拟消费者
 */

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
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                Float money = (Float)args[0];
                if("sellProduct".equals(method.getName())){
                    returnValue = method.invoke(producer, money*0.8f);
                }
                return returnValue;
            }
        });
        proxyProducer.sellProduct(10000);

    }
}
