package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.Carts;
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
import java.util.List;

/**
 * 填写订单
 */
@WebServlet(urlPatterns= "/checkOutOrderServlet")
public class CheckOutOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CartsService cartsService=new CartsServiceImpl();
        //获取数组
        String []pids = request.getParameter("array").split(",");
        String s=request.getParameter("array");
        //获取用户信息
        String username=((User)request.getSession().getAttribute("user")).getUsername();

        //获取购物车总额
        String total=""+cartsService.Total(pids,username);
        System.out.println(total);
        //获取其要购买的商品
        List<Carts> Buycarts=cartsService.findBuyCarts(username,pids);
//System.out.println(Buycarts.size());
        request.setAttribute("total",total);
        request.setAttribute("Cart",Buycarts);
        request.setAttribute("num",Buycarts.size());
//        request.getSession().setAttribute("array",s);
        request.getRequestDispatcher("Order.jsp").forward(request,response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 this.doPost(request,response);
    }
}
