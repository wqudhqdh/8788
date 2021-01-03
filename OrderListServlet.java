package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.Domain.OrderListInformation;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.Domain.receiveAddress;
import cn.itcast.Store.service.CartsService;
import cn.itcast.Store.service.OrderService;
import cn.itcast.Store.service.impl.CartsServiceImpl;
import cn.itcast.Store.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/orderListServlet")
public class OrderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CartsService cartsService=new CartsServiceImpl();
        //订单详情
        OrderListInformation orderListInformation=new OrderListInformation();
        //购买人信息
         User user=((User)request.getSession().getAttribute("user"));
        //获取收货人信息
        receiveAddress raddress=new receiveAddress();
        raddress.setName(request.getParameter("name"));
        raddress.setPhone(request.getParameter("phone"));
        raddress.setAddress(request.getParameter("address"));
        raddress.setPostCode(request.getParameter("postcode"));
    //获取快递公司
        String company=request.getParameter("company");
        //获取购物车的商品编号
        String []pids = request.getParameter("product").split(",");
       //获取要购买的商品
        List<Carts> cartsList=cartsService.findBuyCarts(user.getUsername(),pids);
        //订单号
        String orderNo=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        //订单日期
        String date=new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss").format(new Date());
       orderListInformation.setUser(user);
       orderListInformation.setReceivePerson(raddress);
       orderListInformation.setCartsList(cartsList);
       orderListInformation.setCompany(company);
       orderListInformation.setOrderNo(orderNo);
       orderListInformation.setOrderDate(date);
       orderListInformation.setState("未发货");
        OrderService  orderService=new OrderServiceImpl();
        orderService.save(orderListInformation);
        double total=Double.parseDouble(request.getParameter("totals"));
//        CartsService cartsService= new CartsServiceImpl();
        //从购物车移除
        cartsService.deleteProducts(user.getUsername(),pids);
        //修改库存
        cartsService.updateNum(cartsList);
        System.out.println(total);
//        response.sendRedirect("payServlet");
        request.setAttribute("sum",total);
        request.setAttribute("oderNo",orderNo);
        request.getRequestDispatcher("payServlet").forward(request,response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 this.doPost(request,response);
    }
}
