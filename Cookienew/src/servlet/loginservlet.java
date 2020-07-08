package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");
        if(checkCode_session!= null && checkCode_session.equalsIgnoreCase(checkCode))
        {
            if("zhangsan".equals(username) && "123".equals(password)){
                session.setAttribute("user", username);
               response.sendRedirect(request.getContextPath()+"/success.jsp");
            }
            else
            {
                request.setAttribute("login_error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
        else
        {
            request.setAttribute("cc_error", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
