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

@WebServlet(urlPatterns ="/adminAddServlet")
public class AdminAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cname=request.getParameter("cname");
        String address=request.getParameter("address");
        String discount=request.getParameter("discount");
        String price1=request.getParameter("price1");
        String price2=request.getParameter("price2");
        String img=request.getParameter("img");
        String text=request.getParameter("text");
        String num=request.getParameter("num");

        Product product=new Product();
        product.setTypes(cname);
        product.setSales(0);
        product.setNum(Integer.parseInt(num));
        product.setProductInformation(text);
        product.setPrice2(Double.parseDouble(price2));
        product.setPrice1(Double.parseDouble(price1));
        product.setFreight("运费包邮");
        product.setDiscount(discount);
        product.setImg(img);
        product.setAddress(address);
        System.out.println(product.toString());
        ProductService productService=new ProductServiceImpl();
        productService.addProduct(product);
        out.write("添加成功");
//          request.getRequestDispatcher("ProductManage.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request,response);
    }
}
