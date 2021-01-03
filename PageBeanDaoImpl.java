package cn.itcast.Store.Dao.impl;

import cn.itcast.Store.Dao.PageBeanDao;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PageBeanDaoImpl implements PageBeanDao {
    /**
     * 查找总记录数
     * @param type
     * @return
     */
    @Override
    public int TotalCount(String type) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount=0;

        String sql = "select count(*) from products where type=?";
        try
        {
           pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,type);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
              totalCount=rs.getInt(1);


            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return totalCount;
    }

    /**
     * 查找商品类型
     * @param tid
     * @return
     */
    @Override
    public String findType(String tid) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String type=null;
        String sql = "select cname from category where  cid=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,tid);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                 type=rs.getString(1);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return type;
    }

    @Override
    public List<Product> findByPage(int start, String rows,String type) {
        List<Product> productList=new ArrayList<>();
        int num=Integer.parseInt(rows);
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="select * from products  where type=? limit ? ,?";

        try
        {
    pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,type);
    pstmt.setInt(2,start);
    pstmt.setInt(3,num);

    rs=pstmt.executeQuery();
    while(rs.next()) {
        Product product = new Product();
        product.setPid(rs.getInt(1));
        product.setImg(rs.getString(2));
        product.setAddress(rs.getString(3));
        product.setDiscount(rs.getString(4));
        product.setFreight(rs.getString(5));
        product.setPrice1(rs.getDouble(7));
        product.setProductInformation(rs.getString(6));
        product.setPrice2(rs.getDouble(8));
        product.setTypes(rs.getString(9));
        product.setNum(rs.getInt(10));
        product.setSales(rs.getInt(11));
        productList.add(product);
    }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  productList;
    }
}
