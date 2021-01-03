package cn.itcast.Store.Dao.impl;

import cn.itcast.Store.Dao.CategoryDao;
import cn.itcast.Store.Domain.Category;
import cn.itcast.Store.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//查询所有的商品分类
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
        List<Category> categories=new ArrayList<>();
        String sql="select * from Category";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Category category=new Category();
                category.setCid(rs.getString(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return categories;
    }
}
