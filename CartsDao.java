package cn.itcast.Store.Dao;

import cn.itcast.Store.Domain.Carts;

import java.util.List;

public interface CartsDao {
    //从购物车减去商品数量
    void reduceCount(String username, int pid, String price, int num);

    void addCount(String username, int pid, String price, int num);
//计算总价
    double Total(String[] pids,String username);

    void deleteProduct(String username, String pid);

    List<Carts> finBuyCarts(String username, String[] pids);

    void deleteProducts(String username, String[] pids);

    String checkNum(String[] pids,String username);

    void updateNum(List<Carts> cartsList);
}
