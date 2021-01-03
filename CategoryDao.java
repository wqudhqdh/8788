package cn.itcast.Store.Dao;

import cn.itcast.Store.Domain.Category;

import java.util.List;

public interface CategoryDao {
    //查询所有
    public List<Category> findAll();
}
