package cn.itcast.Store.Dao.impl;

import cn.itcast.Store.Dao.OrderDao;
import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.Domain.OrderListInformation;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.Domain.receiveAddress;
import cn.itcast.Store.util.DBUtil;
import cn.itcast.Store.web.servlet.OrderListServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    /**
     * 保存订单信息
     * @param orderListInformation
     */
    @Override
    public void save(OrderListInformation orderListInformation) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql="insert into order_list(orderNumber,order_create_Date,username,email,telphone,name,address,postalCode,phone,courierCompany,pid,productInformation,price,num,order_state,img)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try
        {
            pstmt = conn.prepareStatement(sql);
            for(Carts carts:orderListInformation.getCartsList()) {

                pstmt.setString(1, orderListInformation.getOrderNo());
                pstmt.setString(2, orderListInformation.getOrderDate());
                pstmt.setString(3,orderListInformation.getUser().getUsername());
                pstmt.setString(4,orderListInformation.getUser().getEmail());
                pstmt.setString(5,orderListInformation.getUser().getTelphone());
                pstmt.setString(6,orderListInformation.getReceivePerson().getName());
                pstmt.setString(7,orderListInformation.getReceivePerson().getAddress());
                pstmt.setString(8,orderListInformation.getReceivePerson().getPostCode());
                pstmt.setString(9,orderListInformation.getReceivePerson().getPhone());
                pstmt.setString(10,orderListInformation.getCompany());
                pstmt.setInt(11,carts.getPid());
                pstmt.setString(12,carts.getProductInformation());
                pstmt.setDouble(13,carts.getPrice2());
                pstmt.setInt(14,carts.getNum());
                pstmt.setString(15,orderListInformation.getState());
                pstmt.setString(16,carts.getImg());
                pstmt.executeUpdate();

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 显示订单信息
     * @param username
     * @return
     */
    @Override
    public     List<OrderListInformation> show(User user) {


        List<OrderListInformation> orderlist=new ArrayList<>();

        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
//        String sql="select * from order_list where email=?";
        String sql="select DISTINCT orderNumber from order_list where email=?";
        String sql2="select * from order_list where email=? and orderNumber=?";
        List<String> number=new ArrayList();
        try
        {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getEmail());
            rs=pstmt.executeQuery();
            //先查找有几次订单
            while(rs.next())
            {
                number.add(rs.getString(1));
            }

            for(String s:number) {
                System.out.println(s);
                receiveAddress address=new receiveAddress();
                List<Carts> cartsList=new ArrayList<>();
                OrderListInformation order=new OrderListInformation();
                order.setUser(user);
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2,s);
                rs=pstmt.executeQuery();

                while(rs.next()) {
                    Carts carts = new Carts();
                    carts.setImg(rs.getString(16));
                    order.setState(rs.getString(15));
                    carts.setNum(rs.getInt(14));
                    carts.setPrice2(rs.getDouble(13));
                    carts.setProductInformation(rs.getString(12));
                    carts.setPid(rs.getInt(11));
                    cartsList.add(carts);
                    order.setCompany(rs.getString(10));
                    address.setPhone(rs.getString(9));
                    address.setPostCode(rs.getString(8));
                    address.setAddress(rs.getString(7));
                    address.setName(rs.getString(6));
                    order.setOrderNo(rs.getString(1));
                    order.setOrderDate(rs.getString(2));
                }
                order.setReceivePerson(address);
                System.out.println(cartsList.size());
                order.setCartsList(cartsList);
                orderlist.add(order);

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
return orderlist;
    }

    /**
     * 显示所有的客户订单
     * @return
     */
    @Override
    public List<OrderListInformation> showAll() {


        List<OrderListInformation> orderlist=new ArrayList<>();

        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
//        String sql="select * from order_list where email=?";
        String sql="select DISTINCT orderNumber from order_list ";
        String sql2="select * from order_list where orderNumber=?";
        List<String> number=new ArrayList();
        try
        {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //先查找有几次订单
            while(rs.next())
            {
                number.add(rs.getString(1));
            }

            for(String s:number) {
                System.out.println(s);
                receiveAddress address=new receiveAddress();
                List<Carts> cartsList=new ArrayList<>();
                OrderListInformation order=new OrderListInformation();
                User user=new User();
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1,s);
                rs=pstmt.executeQuery();

                while(rs.next()) {
                    Carts carts = new Carts();
                    carts.setImg(rs.getString(16));
                    order.setState(rs.getString(15));
                    carts.setNum(rs.getInt(14));
                    carts.setPrice2(rs.getDouble(13));
                    carts.setProductInformation(rs.getString(12));
                    carts.setPid(rs.getInt(11));
                    cartsList.add(carts);
                    order.setCompany(rs.getString(10));
                    address.setPhone(rs.getString(9));
                    address.setPostCode(rs.getString(8));
                    address.setAddress(rs.getString(7));
                    address.setName(rs.getString(6));
                    order.setOrderNo(rs.getString(1));
                    order.setOrderDate(rs.getString(2));
                    user.setUsername(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    user.setTelphone(rs.getString(5));
                }
                order.setReceivePerson(address);
                System.out.println(cartsList.size());
                order.setCartsList(cartsList);
                order.setUser(user);
                orderlist.add(order);

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return orderlist;
    }

    @Override
    public void deliverGoods(String orderNo) {
        Connection coon=DBUtil.getConnection();
        PreparedStatement pstmt=null;
        String sql="update order_list set order_state=? where orderNumber=?";
        try
        {

            pstmt=coon.prepareStatement(sql);
            pstmt.setString(1,"已发货");
            pstmt.setString(2,orderNo);
            pstmt.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String cancelOrder(String state,String orderNo) {
        Connection coon=DBUtil.getConnection();
        PreparedStatement pstmt=null;
        String sql="update order_list set order_state=? where orderNumber=?";
        try
        {
            pstmt=coon.prepareStatement(sql);
            pstmt.setString(1,"已取消");
            pstmt.setString(2,orderNo);
            pstmt.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "取消成功";
    }
}
