package cn.itcast.Store.service.impl;

import cn.itcast.Store.Dao.ProductDao;
import cn.itcast.Store.Dao.impl.ProductDaoImpl;
import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.Domain.Category;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();

    /**
     * 查找所以的热销商品
     *
     * @return
     */
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 查找所有的新品推荐商品
     *
     * @return
     */
    @Override
    public List<Product> findAllNewProduct() {
        return productDao.findAllNewProduct();
    }

    /**
     * 查找商品的所有信息
     *
     * @return
     */
    @Override
    public Product findProduct(String pid) {
        return productDao.findProduct(pid);
    }

    /**
     * 添加到购物车
     *
     * @param product
     * @param num
     */
    @Override
    public String addToShoppingCart(Product product, String num, User user) {
        return productDao.addToShoppingCart(product, num, user);
    }

    @Override
    public List<Carts> findProductInCarts(String username) {
        return productDao.findProductInCarts(username);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }

    /**
     * 从商品表删除选中的商品
     * @param pids
     */
    @Override
    public void deleteProducts(String[] pids) {
        productDao.deleteProducts(pids);
    }

    /**
     * 从商品表添加商品
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        productDao.addProducts(product);
    }

    @Override
    public List<Product> findSearchProducts(String text) {
      return productDao.findSearchProducts(text);
    }

    @Override
    public void updateNum(String nowNum, String pid) {
        productDao.updateNum(Integer.parseInt(nowNum),pid);
    }

    @Override
    public void updatePrice(String price, String pid) {
        productDao.updatePirce(Double.parseDouble(price),Integer.parseInt(pid));
    }


}
