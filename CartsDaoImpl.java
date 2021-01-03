package cn.itcast.Store.Dao.impl;

import cn.itcast.Store.Dao.CartsDao;
import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.util.DBUtil;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartsDaoImpl implements CartsDao {
    /**
     * 从购物车减去商品数量
     *
     * @param username
     * @param pid
     * @param price
     * @param num
     */
    @Override
    public void reduceCount(String username, int pid, String price, int num) {

        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String up = "UPDATE shoppinglist SET num = ? WHERE username = ? and pid=?";
        try {
            pstmt = conn.prepareStatement(up);
            pstmt.setInt(1, num - 1);
            pstmt.setString(2, username);
            pstmt.setInt(3, pid);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在购物出添加数量
     *
     * @param username
     * @param pid
     * @param price
     * @param num
     */
    @Override
    public void addCount(String username, int pid, String price, int num) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String up = "UPDATE shoppinglist SET num = ? WHERE username = ? and pid=?";
        try {
            pstmt = conn.prepareStatement(up);
            pstmt.setInt(1, num + 1);
            pstmt.setString(2, username);
            pstmt.setInt(3, pid);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 计算总价
     *
     * @param pids
     * @return
     */
    @Override
    public double Total(String[] pids, String username) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select price2,num from shoppinglist where username=? and pid=?";
        double total = 0;

        for (int i = 0; i < pids.length; i++) {
            int pid = Integer.parseInt(pids[i]);
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setInt(2, pid);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    double price = rs.getDouble(1);
                    int num = rs.getInt(2);
                    double ww = Math.round((num) * (price * 100)) / 100.0;

                    total += ww;


                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        return Math.round(total * 100) / 100.0;
    }

    /**
     * 从购物车删除商品
     *
     * @param username
     * @param pid
     */
    @Override
    public void deleteProduct(String username, String pid) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "delete  from shoppinglist where username=? and pid=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setInt(2, Integer.parseInt(pid));
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有顾客要买的商品
     * @param username
     * @param pids
     * @return
     */
    @Override
    public List<Carts> finBuyCarts(String username, String[] pids) {
        List<Carts> cartsList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from shoppinglist where username=? and pid=?";

        for (int i = 0; i < pids.length; i++) {
            int pid = Integer.parseInt(pids[i]);
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setInt(2, Integer.parseInt(pids[i]));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    Carts carts=new Carts();
                    carts.setPid(rs.getInt(4));
                    carts.setPrice1(rs.getDouble(5));
                    carts.setPrice2(rs.getDouble(6));
                    carts.setProductInformation(rs.getString(7));
                    carts.setNum(rs.getInt(8));
                    carts.setImg(rs.getString(9));
                    cartsList.add(carts);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  cartsList;
    }
//批量删除
    @Override
    public void deleteProducts(String username, String[] pids) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "delete  from shoppinglist where username=? and pid=?";
        try {
            for(int i=0;i<pids.length;i++)
            {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setInt(2, Integer.parseInt(pids[i]));
                pstmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验库存
     * @param pids
     * @param username
     * @return
     */
    @Override
    public String checkNum(String[] pids,String username) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        String sql1 = "select num,productInformation from products where pid=?";
        String sql2="select num from shoppinglist where username=? and pid=?";
        String productInformation=null;
        boolean flag=false;
        int total=0;
        int num=0;
        StringBuffer s=new StringBuffer();
        try
        {
            for(int i=0;i<pids.length;i++)
            {//查找库存
                pstmt=connection.prepareStatement(sql1);
                pstmt.setInt(1,Integer.parseInt(pids[i]));
                rs=pstmt.executeQuery();
                if(rs.next())
                {
                    total=rs.getInt(1);
                    productInformation=rs.getString(2);
                }
                //查找要买的商品的数量
                pstmt=connection.prepareStatement(sql2);
                pstmt.setString(1,username);
                pstmt.setInt(2,Integer.parseInt(pids[i]));
                rs=pstmt.executeQuery();
                if(rs.next())
                { num=rs.getInt(1);
                }
               if(total>=num)
               {
               System.out.println("11111");
               }
               else
               {
//                   s=productInformation+"\n";
                   s.append(productInformation);
                  System.out.println("222222");
               }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(s);
        return  s.toString();
    }

    /**
     * 修改库存
     * @param cartsList
     */
    @Override
    public void updateNum(List<Carts> cartsList) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int num=0;
        int sales=0;
        int pid;
        String sql = "UPDATE products SET num = ?,sales = ? WHERE  pid=?";
        String sql2="select num ,sales from products where pid=?";
        for(Carts carts:cartsList)
        {
            try {
                 pid = carts.getPid();
                pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1,pid);
                rs=pstmt.executeQuery();
                if(rs.next())
                {
                   num=rs.getInt(1);
                   sales=rs.getInt(2);
                }
                pstmt=connection.prepareStatement(sql);
                pstmt.setInt(1,num-carts.getNum());
                pstmt.setInt(2,sales+carts.getNum());
                pstmt.setInt(3,pid);
                pstmt.executeUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
