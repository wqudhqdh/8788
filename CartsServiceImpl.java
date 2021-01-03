package cn.itcast.Store.service.impl;

import cn.itcast.Store.Dao.CartsDao;
import cn.itcast.Store.Dao.impl.CartsDaoImpl;
import cn.itcast.Store.Domain.Carts;
import cn.itcast.Store.service.CartsService;

import java.util.List;

public class CartsServiceImpl  implements CartsService {
    CartsDao cartsDao=new CartsDaoImpl();
    @Override
    public void reduceCount(String username, int pid, String price, int num) {
        cartsDao.reduceCount(username,pid,price,num);
    }

    @Override
    public void AddCount(String username, int pid, String price, int num) {
        cartsDao.addCount(username,pid,price,num);
    }

    @Override
    public double Total(String[] pids,String username) {
        return cartsDao.Total(pids,username);
    }

    @Override
    public void deleteProduct(String username, String pid) {
        cartsDao.deleteProduct(username,pid);
    }

    @Override
    public List<Carts> findBuyCarts(String username, String[] pids) {
        return cartsDao.finBuyCarts(username,pids);
    }

    @Override
    public void deleteProducts(String username, String[] pids) {
        cartsDao.deleteProducts(username,pids);
    }

    @Override
    public String checkNum(String[] pids,String username) {
        return cartsDao.checkNum(pids,username);
    }

    @Override
    public void updateNum( List<Carts> cartsList) {
        cartsDao.updateNum(cartsList);
    }
}
