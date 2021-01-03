package cn.itcast.Store.service.impl;

import cn.itcast.Store.Dao.PageBeanDao;
import cn.itcast.Store.Dao.impl.PageBeanDaoImpl;
import cn.itcast.Store.Domain.PageBean;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.service.PageBeanService;

import java.util.ArrayList;
import java.util.List;

public class PageBeanServiceImpl implements PageBeanService {
    PageBeanDao  pageBeanDao=new PageBeanDaoImpl();
    @Override
    public PageBean<Product> findPageBean(String currentPage, String rows,String type) {
        PageBean<Product> pageBean=new PageBean<>();
        List<Product> productList=new ArrayList<>();
        //总记录
        int totalCount=pageBeanDao.TotalCount(type);

        //当前页
        int current=Integer.parseInt(currentPage);
        int totalPage;
       //开始
        int start=(current-1)*Integer.parseInt(rows);
        productList=pageBeanDao.findByPage(start,rows,type);

    //总页数
        int row=Integer.parseInt(rows);
//        System.out.println(row);
        if(totalCount%row==0)
        {
            System.out.println("111");
            totalPage=totalCount/row;
        }
        else
        {
            totalPage=totalCount/row+1;
        }


        pageBean.setCurrentPage(current);
        pageBean.setRows(Integer.parseInt(rows));
        pageBean.setList(productList);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;



    }



    @Override
    public String findType(String tid) {
        return pageBeanDao.findType(tid);
    }
}
