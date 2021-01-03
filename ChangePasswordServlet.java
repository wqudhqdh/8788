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

@WebServlet(urlPatterns = "/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String oldPassword=request.getParameter("password");
        String newPassword=request.getParameter("newpassword");
        String password=((User)request.getSession().getAttribute("user")).getPassword();
        String username=((User)request.getSession().getAttribute("user")).getUsername();
        if(oldPassword.compareTo(newPassword)==0)
        {
            out.write("两次密码一样，请重新输入");
        }
        else {
            if (oldPassword.compareTo(password) == 0) {
                UserService userService = new UserServiceImpl();
                userService.updatePassword(username, newPassword);
                out.write("修改成功");
            } else {
                out.write("密码错误");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
