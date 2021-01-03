package cn.itcast.Store.Domain;
//收货人信息
public class receiveAddress {
    private String name;
    private String address;
    private String postCode;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "receiveAddress{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
