package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.PageBean;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.service.PageBeanService;
import cn.itcast.Store.service.impl.PageBeanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/poductByPageServlet")
public class PoductByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("okok");
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PageBeanService pageBeanService=new PageBeanServiceImpl();
        //当前页码
        String currentPage=request.getParameter("currentPage");

        //每页显示的记录数
        String rows=request.getParameter("rows");
        //类型
        String tid=request.getParameter("tid");
        //查找类型
        String type=pageBeanService.findType(tid);

        PageBean<Product> pageBean=pageBeanService.findPageBean(currentPage,rows,type);

            System.out.println(pageBean.getList().size());
            request.setAttribute("pageBean", pageBean);
            request.setAttribute("tid", tid);
            request.getRequestDispatcher("page.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
