package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.UserService;
import cn.itcast.Store.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/loginUserServlet")
public class LoginUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        User user = userService.Login(email, password);
//        System.out.println(user.getPower());
        HttpSession httpSession=request.getSession();
        if(user!=null) {
            httpSession.setAttribute("user", user);
            out.write(user.getPower()+"");
        }
        else
        {
            out.write("用户名或密码错误");
        }



    }
}
