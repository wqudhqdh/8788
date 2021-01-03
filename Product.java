package cn.itcast.Store.Domain;

public class Product {
    private int pid;
    private String img;
    private String address;
    private String discount;
    private String freight;
    private String productInformation;
    private double price1;
    private double price2;
    private String types;
    private int num;
    private int sales;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getProductInformation() {
        return productInformation;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", img='" + img + '\'' +
                ", address='" + address + '\'' +
                ", discount='" + discount + '\'' +
                ", freight='" + freight + '\'' +
                ", productInformation='" + productInformation + '\'' +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", types='" + types + '\'' +
                ", num=" + num +
                ", sales=" + sales +
                '}';
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

}
