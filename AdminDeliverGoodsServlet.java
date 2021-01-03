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

@WebServlet(urlPatterns = "/adminDeliverGoodsServlet")
public class AdminDeliverGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String orderNo=request.getParameter("orderNo");
        String state=request.getParameter("state");
        OrderService orderService=new OrderServiceImpl();
        if(state.compareTo("未发货")==0) {
            //发货
            orderService.deliverGoods(orderNo);
            out.print("<script language='javascript'>alert('发货成功');window.location.href='AllOrderShow.jsp';</script>");
        }
       else if(state.compareTo("已发货")==0){
            out.print("<script language='javascript'>alert('货已发出');window.location.href='AllOrderShow.jsp';</script>");

//            request.getRequestDispatcher("AllOrderShow.jsp").forward(request, response);
        }
        else
        {
            out.print("<script language='javascript'>alert('客户订单已取消');window.location.href='AllOrderShow.jsp';</script>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
