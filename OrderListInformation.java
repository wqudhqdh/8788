package cn.itcast.Store.Domain;

import java.util.List;

//订单信息
public class OrderListInformation{
    private User user;//购买人信息
    private receiveAddress receivePerson;//收货人信息
    private String company;//快递公司
    private List<Carts> cartsList;//购买的商品
    private String OrderNo;//订单号
    private String OrderDate;//订单时间
    private String state;//订单状态
    private double total;//总金额
    public receiveAddress getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(receiveAddress receivePerson) {
        this.receivePerson = receivePerson;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Carts> getCartsList() {
        return cartsList;
    }

    public void setCartsList(List<Carts> cartsList) {
        this.cartsList = cartsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderListInformation{" +
                "user=" + user +
                ", receivePerson=" + receivePerson +
                ", company='" + company + '\'' +
                ", cartsList=" + cartsList +
                ", OrderNo='" + OrderNo + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
