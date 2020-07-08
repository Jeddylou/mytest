package com.itheima.proxy;

/**
 * 一个生产者
 */

public class Producer implements IProducer{

    public void sellProduct(float money){
        System.out.println("拿到钱，销售产品" + money);
    }

    public void afterService(float money){
        System.out.println("提供售后服务，并拿到钱" + money);
    }
}
