package cn.itcast.Store.Dao;



import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.Domain.Product;
import cn.itcast.Store.Domain.User;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    List<Product> findAllNewProduct();

    Product findProduct(String pid);

    String addToShoppingCart(Product product, String num, User user);

    List<Carts> findProductInCarts(String username);

    List<Product> findAllProducts();

    void deleteProducts(String[] pids);

    void addProducts(Product product);

    List<Product> findSearchProducts(String text);

    void updateNum(int num, String pid);

    void updatePirce(double parseDouble, int parseInt);
}
