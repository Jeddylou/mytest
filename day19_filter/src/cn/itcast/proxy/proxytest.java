package cn.itcast.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxytest {
    public static void main(String[] args) {
        //真实对象
        Lenovo lenovo = new Lenovo();

        //动态代理，增强这个对象
        //三个参数，类加载器（真实对象）， 接口数组（真实对象）， 处理器
        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            //代理逻辑编写的方法，代理对象调用的所有方法都会触发该方法执行
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("该方法执行了");
//                System.out.println(method.getName());
//               System.out.println(args[0]);

                //1.增强参数
                //判断是否是sale方法
                if(method.getName().equals("sale")){
                    double money = (double) args[0];
                    money *= 0.85;
                    System.out.println("专车接");
                    //使用真实对象调用这个方法
                    String obj = (String) method.invoke(lenovo, money);
                    System.out.println("免费送货");


                    //增强返回值
                    return obj + "_鼠标垫";
                }
                else{
                    //使用真实对象调用
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }

            }
        });

        String computer = proxy_lenovo.sale(8000);
       System.out.println(computer);
//        proxy_lenovo.show();
    }
}
