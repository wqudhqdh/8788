package cn.itcast.Store.Dao;

import cn.itcast.Store.Domain.OrderListInformation;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.web.servlet.OrderListServlet;

import java.util.List;

public interface OrderDao {
    void save(OrderListInformation orderListInformation);

    List<OrderListInformation> show(User user);

    List<OrderListInformation> showAll();

    void deliverGoods(String orderNo);

    String cancelOrder(String state,String orderNo);
}
