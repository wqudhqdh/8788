package cn.itcast.Store.service.impl;

import cn.itcast.Store.Dao.CategoryDao;
import cn.itcast.Store.Dao.impl.CategoryDaoImpl;
import cn.itcast.Store.Domain.Category;
import cn.itcast.Store.service.Categoryervice;

import java.util.List;

public class CategoryServiceImpl implements Categoryervice {
    CategoryDao categoryDao=new CategoryDaoImpl();
    /**
     * 查询所有商品条目
     * @return
     */

    @Override
    public List<Category> findAll() {

        return categoryDao.findAll();
    }
}
