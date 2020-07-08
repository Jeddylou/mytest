package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    //注册功能
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo inof = new ResultInfo();
            inof.setFlag(false);
            inof.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(inof);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return ;
        }

        //获取数据

        Map<String, String[]> map = request.getParameterMap();

        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service完成注册

//        UserService service =  new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo inof = new ResultInfo();

        //响应结果
        if(flag){
            inof.setFlag(true);

        }else{
            inof.setFlag(false);
            inof.setErrorMsg("注册失败");

        }

        //将inof对象转换成json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(inof);

        //写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    //登录功能
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        UserService service = new UserServiceImpl();
        User u = service.login(user);
        //判断

        ResultInfo info = new ResultInfo();
        if(u == null){
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }

        //是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("尚未激活");
        }
        //登录成功
        if(u != null && "Y".equals(u.getStatus())){
            info.setFlag(true);
            request.getSession().setAttribute("user",u);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);

    }

    //查询单个
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        System.out.println(user);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);

    }

    //退出
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        response.sendRedirect(request.getContextPath()+"/login.html");

    }

    //激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code != null){
//            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);

            String msg = null;

            if(flag){
                msg = "激活成功<a href='login.html'>请登录</a>";
            }else{
                msg = "激活失败";
            }

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

}
