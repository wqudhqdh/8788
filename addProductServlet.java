package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.ProductService;
import cn.itcast.Store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/addProductServlet")
/**
 * 添加到购物车
 */
public class addProductServlet extends HttpServlet {
    private ProductService productService=new ProductServiceImpl() ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String pid=request.getParameter("pid");
        System.out.println(pid);

        String num=request.getParameter("num");

       User user=(User) request.getSession().getAttribute("user");
        //查询到每个商品的对应的信息
        Product product=productService.findProduct(pid);
        //把其添加到购物车中
        String s=productService.addToShoppingCart(product,num,user);
        System.out.println(s);
        out.write(s);
//        out.write("添加成功");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          this.doPost(request,response);
    }
}
