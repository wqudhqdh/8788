package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.CartsService;
import cn.itcast.Store.service.impl.CartsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

@WebServlet(urlPatterns = "/showToCheckOutServlet")
public class ShowToCheckOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        //获取数组
        String[] pids = request.getParameterValues("array[]");
        CartsService cartsService=new CartsServiceImpl();
        String username=((User)request.getSession().getAttribute("user")).getUsername();
        String total=""+cartsService.Total(pids,username);


        out.write(total);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request,response);
    }
}
