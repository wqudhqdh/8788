package cn.itcast.Store.Dao.impl;

import cn.itcast.Store.Dao.UserDao;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {


    /**
     * 判断邮箱跟用户名之前是否注册过
     */
    @Override
    public boolean isRegister(User user) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE email=? or username=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2,user.getUsername());

            rs = pstmt.executeQuery();
            if (rs.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return true;
    }

    //保存到数据库
    @Override
    public void save(User user) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        String sql = "insert into  User(phone,username,password,email,power)values(?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getTelphone());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setInt(5,user.getPower());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }

    @Override
    public User login(String email, String password) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
//        String sql = "SELECT username FROM user WHERE email=? and password=?";
        String sql = "SELECT * FROM user WHERE email=? and password=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User user=new User();
                user.setTelphone(rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPower(rs.getInt(5));
//                rs.getString(1);
               return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return null;
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="update user set password=? where username=?";
        try
        {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,newPassword);
            pstmt.setString(2,username);
            pstmt.executeUpdate();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
