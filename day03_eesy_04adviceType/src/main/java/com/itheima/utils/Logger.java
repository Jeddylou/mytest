package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类
 */
public class Logger {

    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行
     */
    public void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了.....");

    }

    public void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了.....");

    }

    public void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了.....");

    }

    public void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了.....");

    }

    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            Object[] args = pjp.getArgs();
            System.out.println("1");
            rtValue = pjp.proceed(args);
            System.out.println("2");

        }catch (Throwable t){
            System.out.println("3");
        }finally {
            System.out.println("4");
        }
        return rtValue;

    }


}
