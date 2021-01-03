package cn.itcast.Store.Dao;

import cn.itcast.Store.Domain.PageBean;
import cn.itcast.Store.Domain.Product;

import java.util.List;

public interface PageBeanDao {
    //获取总商品
 int TotalCount(String type) ;
//获取类型

    String findType(String tid);
//获取list集合
    List<Product> findByPage(int start, String rows,String type);
}
