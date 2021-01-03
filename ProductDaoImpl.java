package cn.itcast.Store.Dao.impl;

import cn.itcast.Store.Dao.ProductDao;
import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.util.DBUtil;
import org.apache.commons.collections.list.PredicatedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    /**
     * 查找所有的热销商品
     *
     * @return
     */
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from products  order by sales desc limit 8";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
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

                products.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    /**
     * 新品推荐
     *
     * @return
     */
    @Override
    public List<Product> findAllNewProduct() {
        List<Product> newProducts = new ArrayList<>();
        String sql = "select * from products order by pid desc limit 8 ";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1,"8");
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
                newProducts.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newProducts;
    }

    /**
     * 通过商品Id查找商品信息
     *
     * @param pid
     * @return
     */
    @Override
    public Product findProduct(String pid) {
        Product product = new Product();
        String sql;

        sql = "select * from products where pid=?";


        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pid);
            rs = pstmt.executeQuery();
            while (rs.next()) {

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


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    /**
     * 添加到购物车
     */
    @Override
    public String addToShoppingCart(Product product, String num, User user) {
        Connection connection = DBUtil.getConnection();
        boolean flag=false;
        PreparedStatement pstmt = null;
        int totalNum=0;
        ResultSet rs = null;
        String find = "select * from shoppinglist where pid=? and username=?";
        String up = "UPDATE shoppinglist SET num = ? WHERE username = ? and pid=?";
        String sql2 = "insert into shoppinglist(username,telphone,email,pid,price1,price2,productInformation,num,img)values(?,?,?,?,?,?,?,?,?)";
        String sql3 = "select num from products where pid=?";
        try {
            //查找库存
            pstmt = connection.prepareStatement(sql3);
            pstmt.setInt(1, product.getPid());
            rs = pstmt.executeQuery();
            if(rs.next()) {
               totalNum = rs.getInt(1);
            }
            System.out.println(totalNum);
            //判断库存是否足够
            if (totalNum >= Integer.parseInt(num)) {
                flag=true;
                //查找商品是否添加到购物车
                pstmt = connection.prepareStatement(find);
                pstmt.setInt(1, product.getPid());
                pstmt.setString(2, user.getUsername());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    //如果已经添加到购物车则直接修改数量
                    pstmt = connection.prepareStatement(up);
                    pstmt.setInt(1, Integer.parseInt(num) + rs.getInt(8));
                    pstmt.setString(2, user.getUsername());
                    pstmt.setInt(3, product.getPid());
                    pstmt.executeUpdate();
                } else {
                    //直接插入
                    pstmt = connection.prepareStatement(sql2);
                    pstmt.setString(1, user.getUsername());
                    pstmt.setString(2, user.getTelphone());
                    pstmt.setString(3, user.getEmail());
                    pstmt.setInt(4, product.getPid());
                    pstmt.setDouble(5, product.getPrice1());
                    pstmt.setDouble(6, product.getPrice2());
                    pstmt.setString(7, product.getProductInformation());
                    pstmt.setInt(8, Integer.parseInt(num));
                    pstmt.setString(9, product.getImg());
                    pstmt.executeUpdate();
                }
            }
     else
            {
                flag=false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag) {
            return "添加成功";
        }
        else
        {

            return "库存不足";
        }
    }


    /**
     * 查找购物车里面的所有商品
     */
    @Override
    public List<Carts> findProductInCarts(String username) {
        Connection connection=DBUtil.getConnection();
        List<Carts> cartsList=new ArrayList<>();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from shoppinglist where username=?";
        try{
          pstmt=connection.prepareStatement(sql);
          pstmt.setString(1,username);
         rs= pstmt.executeQuery();
          while(rs.next())
          {
              Carts carts=new Carts();
carts.setPid(rs.getInt(4));
              carts.setPrice1(rs.getDouble(5));
              carts.setPrice2(rs.getDouble(6));
              carts.setProductInformation(rs.getString(7));
              carts.setNum(rs.getInt(8));
              carts.setImg(rs.getString(9));
              cartsList.add(carts);

          }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return cartsList;
    }

    /**
     * 查找所有的商品
     * @return
     */
    @Override
    public List<Product> findAllProducts() {
        List<Product> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();

        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from products";
        try
        {

            pstmt=connection.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
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
                list.add(product);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除选中的商品
     * @param pids
     */
    @Override
    public void deleteProducts(String[] pids) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "delete  from products where pid=?";
        try {
            for(int i=0;i<pids.length;i++) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(pids[i]));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加商品到商品表
     * @param product
     */
    @Override
    public void addProducts(Product product) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String s=product.getImg();
        String  img=s.substring(s.lastIndexOf('\\'));
        System.out.println(img);

        String sql = "insert into products(img,address,discount,freight,productInformation,price1,price2,type,num,sales)values(?,?,?,?,?,?,?,?,?,?)";
        try
        {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,img);
            pstmt.setString(2,product.getAddress());
            pstmt.setString(3,product.getDiscount());
            pstmt.setString(4,product.getFreight());
            pstmt.setString(5,product.getProductInformation());
            pstmt.setDouble(6,product.getPrice1());
            pstmt.setDouble(7,product.getPrice2());
            pstmt.setString(8,product.getTypes());
            pstmt.setInt(9,product.getNum());
            pstmt.setInt(10,product.getSales());
            pstmt.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    @Override
    public List<Product> findSearchProducts(String text) {
          List<Product> productList=new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        ResultSet rs=null;
        PreparedStatement pstmt = null;
        String s="%"+text+"%";
        String sql="select * from products where productInformation like ?";
        try
        {

            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,s);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                Product product=new Product();
                product.setPid(rs.getInt(1));
                product.setImg(rs.getString(2));
                product.setAddress(rs.getString(3));
                product.setDiscount(rs.getString(4));
                product.setFreight(rs.getString(5));
                product.setProductInformation(rs.getString(6));
                product.setPrice1(rs.getDouble(7));
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
        return productList;
    }

    /**
     * 修改库存
     * @param parseInt
     * @param pid
     */
    @Override
    public void updateNum(int num, String pid) {
        Connection coon=DBUtil.getConnection();
        PreparedStatement pstmt=null;
        String sql="update products set num=? where pid=?";
        try
        {
            pstmt=coon.prepareStatement(sql);
            pstmt.setInt(1,num);
            pstmt.setInt(2,Integer.parseInt(pid));
            pstmt.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 修改价格
     * @param parseDouble
     * @param parseInt
     */
    @Override
    public void updatePirce(double price, int pid) {
        Connection coon=DBUtil.getConnection();
        PreparedStatement pstmt=null;
        String sql="update products set price2=? where pid=?";
        try
        {
            pstmt=coon.prepareStatement(sql);
            pstmt.setDouble(1,price);
            pstmt.setInt(2,pid);
            pstmt.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
