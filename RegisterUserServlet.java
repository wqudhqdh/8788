package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.UserService;
import cn.itcast.Store.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet(urlPatterns = "/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        //获取请求参数
String phone=request.getParameter("phone");
String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
//封装对象
        User user=new User();
        user.setTelphone(phone);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPower(0);

        UserService userService=new UserServiceImpl();
       boolean flag= userService.register(user);
//如果没有被注册过，则保存到数据库
       if(flag)
{
    userService.save(user);
    response.getWriter().write("注册成功");
}
else {
    response.getWriter().write("您的用户名或邮箱已注册");
}

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
