package com.itheima.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了，开始事务，提交事务，回滚事务和释放连接
 */

public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void beginTransaction(){

        try{
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void commit(){

        try{
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
    }catch (Exception e){
        e.printStackTrace();
    }
    }

    public void release(){
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
