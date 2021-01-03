package cn.itcast.Store.web.servlet;

import cn.itcast.Store.service.OrderService;
import cn.itcast.Store.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/cancelOrderServlet")
public class cancelOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String state=request.getParameter("state");
        String ordeNo=request.getParameter("orderNo");
        OrderService orderService=new OrderServiceImpl();
        if(state.compareTo("未发货")==0) {
            String s = orderService.cancelOrder(state, ordeNo);
            out.print("<script language='javascript'>alert('取消成功');window.location.href='OrderShow.jsp';</script>");

        }
        else if(state.compareTo("已发货")==0){
            out.print("<script language='javascript'>alert('商家已发货');window.location.href='OrderShow.jsp';</script>");

        }
        else {
            out.print("<script language='javascript'>alert('你已取消订单');window.location.href='OrderShow.jsp';</script>");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
