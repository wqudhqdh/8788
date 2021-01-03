package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.OrderListInformation;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.OrderService;
import cn.itcast.Store.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/showAllOrderServlet")
public class ShowAllOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User user=((User)request.getSession().getAttribute("user"));
        OrderService orderService=new OrderServiceImpl();
        List<OrderListInformation> orderList=  orderService.showAllOrder();
        System.out.println(orderList);
        request.setAttribute("allorderlist",orderList);
        request.getRequestDispatcher("AllOrderShow.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
