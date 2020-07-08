package cn.itcast.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名
        String username = request.getParameter("username");
        //设置数据格式为json
        response.setContentType("application/json;charset=utf-8");

        Map<String, Object> map = new HashMap<String, Object>();


        //调用service判断用户名是否存在
        if("tom".equals(username)){
            map.put("userExist", true);
            map.put("msg", "此用户太欢迎换一个");
        }else{
            map.put("userExist", false);
            map.put("msg", "可用");

        }


        //转为json 传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
