package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.service.ProductService;
import cn.itcast.Store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns= "/productInfoServlet")
public class productInfoServlet extends HttpServlet {
    private ProductService productService=new ProductServiceImpl() ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       //获取到每个商品对应的ID
        String pid=request.getParameter("pid");


      //查询到每个商品的对应的信息
        Product product=productService.findProduct(pid);
        System.out.println(product);

        request.setAttribute("product",product);

       request.getRequestDispatcher("productInfo.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
