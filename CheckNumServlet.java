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

@WebServlet(urlPatterns = "/checkNumServlet")
public class CheckNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String []pids=request.getParameter("arrays").split(",");
        CartsService cartsService=new CartsServiceImpl();
        User user=(User)request.getSession().getAttribute("user");
        //校验库存
        String s=cartsService.checkNum(pids,user.getUsername());
        if(s.length()<=1)
        {
            out.write("库存足够");
        }
        else {
            out.write(s + "库存不足");
        }
//        if(flag)
////        {
////            out.write("库存足够");
////        }
////        else
////        {
////            out.write("库存不足");
////        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
