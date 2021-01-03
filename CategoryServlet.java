package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.Category;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.service.Categoryervice;
import cn.itcast.Store.service.ProductService;
import cn.itcast.Store.service.impl.CategoryServiceImpl;
import cn.itcast.Store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/categoryServlet")
public class CategoryServlet extends HttpServlet {
   private Categoryervice categoryervice=new CategoryServiceImpl();
   private ProductService productService=new ProductServiceImpl() ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //设置请求和响应编码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
             //商品分类
            List<Category> categoryList=categoryervice.findAll();
            //热销商品
            List<Product> productList=productService.findAll();
            //新品推荐
            List<Product> newProductList=productService.findAllNewProduct();
            HttpSession httpSession=request.getSession();
        httpSession.setAttribute("Category",categoryList);
        request.setAttribute("Product",productList);
        request.setAttribute("newProduct",newProductList);
        request.getRequestDispatcher("homePage.jsp").forward(request,response);


    }
}
