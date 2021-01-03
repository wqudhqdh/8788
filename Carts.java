package cn.itcast.Store.Domain;

public class Carts {


    private  int pid;
    private double price1;
    private double price2;
    private String productInformation;
    private int num;
    private String img;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "pid=" + pid +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", productInformation='" + productInformation + '\'' +
                ", num=" + num +
                ", img='" + img + '\'' +
                '}';
    }

}
