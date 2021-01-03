package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.service.ProductService;
import cn.itcast.Store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns= "/productServlet")
public class ProductServlet extends HttpServlet {
    private ProductService productService=new ProductServiceImpl() ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      List<Product> productList=productService.findAllProducts();
      request.setAttribute("products",productList);
      request.getRequestDispatcher("ProductManage.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 this.doPost(request,response);
    }
}
