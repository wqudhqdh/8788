package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.CartsService;
import cn.itcast.Store.service.ProductService;
import cn.itcast.Store.service.impl.CartsServiceImpl;
import cn.itcast.Store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns= "/reduceProductServlet")
public class ReduceProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int pid=Integer.parseInt(request.getParameter("id"));//获取商品id
        String price=request.getParameter("price");//获取商品价格
        int  num=Integer.parseInt(request.getParameter("num"));//数量

        CartsService cartsService=new CartsServiceImpl();
        String username=((User)request.getSession().getAttribute("user")).getUsername();
        cartsService.reduceCount(username,pid,price,num);
     out.write("sucess");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
