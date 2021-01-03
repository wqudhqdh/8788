package cn.itcast.Store.service.impl;

import cn.itcast.Store.Dao.OrderDao;
import cn.itcast.Store.Dao.impl.OrderDaoImpl;
import cn.itcast.Store.Domain.OrderListInformation;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.OrderService;
import cn.itcast.Store.web.servlet.OrderListServlet;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao=new OrderDaoImpl();
    /**
     * 保存订单信息到数据库
     */

    @Override
    public void save(OrderListInformation orderListInformation) {
        orderDao.save(orderListInformation);
    }

    /**
     * 显示个人订单
     * @param user
     * @return
     */
    @Override
    public     List<OrderListInformation> showMyOrder(User user) {
        return orderDao.show(user);
    }

    /**
     * 显示所有订单
     * @return
     */
    @Override
    public List<OrderListInformation> showAllOrder() {
        return orderDao.showAll();
    }

    /**
     * 发货
     * @param orderNo
     */
    @Override
    public void deliverGoods(String orderNo) {
        orderDao.deliverGoods(orderNo);
    }

    /**
     * 取消订单
     * @param state
     * @return
     */
    @Override
    public String cancelOrder(String state,String ordeNo) {
        return orderDao.cancelOrder(state,ordeNo);
    }
}
