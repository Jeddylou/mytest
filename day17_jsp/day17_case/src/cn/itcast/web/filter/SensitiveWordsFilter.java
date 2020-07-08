package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//铭感词汇过滤
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //创建代理，增强getParameter
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是否是getParameter

                if(method.getName().equals("getParameter")){
                    System.out.println("有吗");
                    String value = (String) method.invoke(req, args);
                    if(value != null){
                        for (String str : list) {
                            if(value.contains(str)){
                                value = value.replaceAll(str, "***");
                            }

                        }
                    }
                    System.out.println(value);
                    return value;
            }
                if(method.getName().equals("getParameterMap")){

                }

                return method.invoke(req, args);

            }
        });
        chain.doFilter(proxy_req, resp);
    }
//public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//    //1.创建代理对象，增强getParameter方法
//
//    ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            //增强getParameter方法
//            //判断是否是getParameter方法
//            if(method.getName().equals("getParameter")){
//                //增强返回值
//                //获取返回值
//                String value = (String) method.invoke(req,args);
//                if(value != null){
//                    for (String str : list) {
//                        if(value.contains(str)){
//                            value = value.replaceAll(str,"***");
//                        }
//                    }
//                }
//
//                return  value;
//            }
//
//            //判断方法名是否是 getParameterMap
//
//            //判断方法名是否是 getParameterValue
//
//            return method.invoke(req,args);
//        }
//    });
//
//    //2.放行
//    chain.doFilter(proxy_req, resp);
//}

    private List<String> list = new ArrayList<String>();
    public void init(FilterConfig config) throws ServletException {
        //加载文件
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
        //读取文件
        try {
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while((line = br.readLine()) != null){
                if(line != null && !line.equals("")){
                    list.add(line);
                }

            }
            br.close();
            System.out.println(list);
            //每一行添加

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
