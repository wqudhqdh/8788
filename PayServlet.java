package cn.itcast.Store.web.servlet;

import cn.itcast.Store.Domain.User;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(urlPatterns = "/payServlet")
public class PayServlet extends HttpServlet {
    private final String APP_ID = "2021000116685159";
    //    私钥
    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPcUJrYFMeBxTmDSLvR2u1CkOJoGjp8xylciA5c5MRiR4+IyN788nThFD0wma6XDNetNVrwg0V0ESlLPCXKrmgfy0G3znH6izqRmp6EYC40fcrkIXBHL64+1EmzU1IG9Mv5TnN5m3WrnMM8QtCAbs0FAL3ZKwqSIZKUDxditmornl+cA9ye+8AwXgONgKwKePT2pLMVxroEIKaQURm86aZKOir9qVXQa+f8BOlQcXn0NzBN8Z+NkRMpKRlE+uMymwGLMpR2hs8fmyfssyicRTAalk41TBoCbSmBST6L3hq3ALBWovCGVwAl3ngL3RpFkUgnO5TC8Igf7JiL2ad3bwvAgMBAAECggEBAINxD6IBVGjRqG7eXsLQuXvd+eFlYn3uzrM0jx7WL1AES/g4js2IqcrvFpgSg7wFvPTt1Bu4Z/EzNZP+SEDdX9gYcZQ4fWQZzCqpd+x/mWrCzDHZbxd0VbiAYu6hdk27YZwj+wIYQ1JT7Q1Q3DYSDA8+vzEfyTu91YDHYWl+UNFmG5gLN+7Y9r2B5Sm78/ryZ07F98E80+1RPytnskqZbZVMYqC+4lRJdFa5es8D8oDO3+q/sy6qdbEQhwxzPodyit2e+9Pth3u4yegqIdOw4+hdeMeBWTmF+YXjqnbdMpe+xXWlJgZmOfF19vLWoH9MMRq0jZUnu3gjVNLmEkjhV/ECgYEA4ypivaF1BtckBDj+eSM31DRtBoEp/QgxGIXxR/LhNkCF3CY+cDoGtOWdL6nFCO5z3KskocSsyWoqJdUoDMFLIr2LsZTuQKZKehSwE4edbaRXqxBZDP6WL2Mgu2GEymbv3PrgCc1rC6F3GYwtdYFrhMX0m4E2OD2et3G2CHDTw7MCgYEAoaZWltcIo6yGGzoue/lQRupJS28O6fGvs+RIoTpCvpVHsmAu43wJYgp/97v1l2pSiUhi5rutsgw4HPTN49BGicM4a8HInqvs1iPVOuz/j8cxs0ckhFp9/FBvN53eam/Z+5asLfaBHARPwGrd7S4wNdnYQxdQCon8pFOBHNb5V5UCgYBm3fG6p1tkDpZLpzdsyYrleBwKvwCxiT0aOsUo7I4ZUByNtW++yDfzQKruQ3Vq9vfWbFV5P6NyzbVqTl0FcZ5BjA2vC57b/bFqGOYm4dljJfFhEFUS8lI2ATB4Sc+lC/oAWkaLic64hJR3KCgJtuJmTHHY3SkdTKILtd1VcyohywKBgA0RV1qmU4p3vfehgnR/OSAifH5eogQDh8KxJ7Xw8chaZQtkfyXgtxl349RxG298JuP2OBiS/32vQzIEEEjkY29rmroLVl6NY34EM46p7hb/cWMe0zLn5dtp3TPPzVUpaaPPEGg+zz0mlBPoD7zGyRrxW2xBDDVt5kCah4t/TXTpAoGACYIUDUfBUDIBKDbYsLaf33SwpCC7Rhfez0vQ4u2HVtkb/l4BwhmMlqz86Vzs3eMitmLySH0Ody16XZedQxYqrtvINB/OmrvhrmNsBHyRA4LjNXHfjK8ENtm8CxpKx5mKm/8zIXAeQkWEbTdv4Uwsdn14Re3Ak6xlAGU4mUn7Jg4=";
    //    private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC+u9fJ7gQtlU761V5USwWpQl7CVdMKwxH3DhFyMHPeEFZQC4ciFkULY12XA0tyCFQna7xm1rI7VYuugmM/TVdv6ud5/i2SkKQc86c7YRcykaNHWqXuSzmj+Ox2RgToxzEcHMTv2kF1bv/Rn6OwuDfWPmogSmPDuwFOjLUf0dEjZFp/6xKcxygyO5JoGL/QqIQCICB4w1habeb2aXP8aOjqwmVCuNQ2R7LD0phrt7PRcPBRPj9dLhgOTNESMHugXJ/IzLfDFLtK3k8FuTxZOai14QVUnlHOKaCQddoouOwltuRpKF1A6spMW7EjiEsEVkAWsZhvh86qo5mklZ5i0WfrAgMBAAECggEALBCv0Qo5UuKs5YyIB4dxnmIkDFfRsynQK7jHVQ9WpZY2qrWPGdP1TaU+SRZMxuKftp/QXh35/XVNGRssnMJckhG+OtP7aWIbWEj+eNx8hIVbKLALp9sfTP/EearBlZn3gZwv744IE356gc6U/c4BsHCpCh7Fn8HDkoc1jU1nOPqfCX1RMudtaP0wriv+4uKpNzHPR2dV3iWTm++zGRzyzJdu1NTWnhJs1MlVtha/8Mp18npxE8Ygjj2O4gabqaMyIoqegcwkvOl5BX77pPeW5fNHeW9iPWlzb21QYXJttYDjm9cErjHmne6rHcwUuMSui6KlH3L9PxgmAS/HGHVn8QKBgQDsvBk2xPO2g79jXHv4Ie8xSeg8b/3ztWyGWL8sQJkLBFyzyHc2kKCf1/zonS16+beDbawP2+mZf8o7A0cdF6ltYK8BG/QZGXIJ0f1joDqLCVKunqNXxFesQ6EG1gugJZ5gAUn23Lq6M/N6ABoyXBST7aEAPISsjWUe1yYxigpTGQKBgQDOQWeIAsLRhkSyu8vmoCyMMQG6Lxt/6JhRHqYndb7b7ffNgZDnzlCkqY24EUirlhk+86B2lNffniXYXEo9K75gc252yQiMQ0Ygqj1xJY6hi5U62gwGc7xDpnJTA6LyW1HdyNuZ9NLWORW9FysqrvZK4oXE6ytcpbvH/14StSlXowKBgBShI5VZPl+mRXRUJ91sn5ps9Vbm73tgUO+4JhimHHAQVj+4dWME4WpsePaQU9lGj45dbUQIhufAchAIbhcD69jDNxziobfaNszSRsF6wf9BtbnPyy4nxvgVAKBG4MZ44L8+/YIMpBz7z/jOr3m5+nUVnRUXMPyUab5xQzvH6rRpAoGAH458M6AQhJQhcKMw+DErf6uD0yOf9nr0yvyAeWsXb3jCQCwBqqWzSu4yI6ZpUu9dH3eQ5nOxb6I3shm0thU6RHGExiHN7//e++JwEh6n6ul7RIzV6CrO9B7EQRU+WgxEw0UW7EA7/dCwFcU0sowgXKa5xr4gXUVyLJBSqVQUZjcCgYB5Uu9/3L/hr0VI5IwA/567wP7+WK1tudZs6nsjpxcC4g0gDLWpG3JlGROQweIMMTr9Qwu8lKhYEYlELNRMfXn7P+Wm5r4mCpanhpTN+DsfebwQ939X3UuLdVymdn7FOwi/IEFxd9eTBzOZxIeWwHUlXLmxLszD9jyrY0fslpI3Ww==";
    private final String CHARSET = "UTF-8";
    //    公钥
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAljVfHixlR8U1sRJ99XTc1723hXmNLqGW7Esk/UhVcrktlnVwUmCSCI9Rmg9yrEOACHViG80Lhu7Zs9nc9MRzG2xhxF++rA6d0ilUoYq3c5KwcFzdU/nLTmtyfuDq5aOdkvGAcBuZyeLqfwUWdkCfBhFYP2H/J0RG9HbR/9IxOgd47cCC3yffco24v/W8/Y3RPwdbKpMzcCKnAciQJahvOBGJlBDL1fhQWxktsdLPwB2SJ0N6uYfXZ68MmyodCJwP+jg/EjSFi6JO0VPP9wj1s3W6WOVkbWgD2LB9YdRg4U3kpGlQNQYHi63FGtBw63uJp2TiGspxe9lQ1Y1inI49aQIDAQAB";
    //    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgxJ7zfe9FLnAoVvamBHWXjCkQmPY+u59MZGQ/aasq5UXUqvp99eBKyNq8NIg9Rq+BRxwsYXR4CUgyNbon4s8RDPm0OMt+yLOUblUgqpJ0BCHCnljHCAPoLnH153tpr144NgD/e30Rz2LLofsn8RQsrrNx4MDYktiVeIVtuUaem/CI0z3+I05qlWPsUMzLagato/eUO0wrh7zyjDTw7grGfvgWvU3JCzWmmAJJaELj+mcxjDPJDhGnTmT+saq2kxYHTLem5lSTSHtH6urBLrq4Cc7yYvAsmMnesuwYKy1P+Qp0J/R/D/5t0k3QgNqF0eDYLXuH9uiWqp7f691U8WSNQIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://127.0.0.1/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8080/hh/homePage.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

//        String OrderNo=new Date().toString();
//        System.out.println("orderNo:"+OrderNo);
//        HttpSession session = request.getSession();
//        User user1 = (User) session.getAttribute("user");
////        User user=userService.findUserByUserAndPass(user1);
//        session.setAttribute("user",user);
//
//        int discount = orderService.findLevelDiscount(user.getLevel());
//
//        List<Order> orders = orderService.findOrderByOrderNo(orderNo);
//        int sumPrice = 0;
//        for(Order order:orders){
//            sumPrice += order.getGoods_price()*order.getGoods_count();
//        }
//        double sum = sumPrice*(discount*0.01f);
//        String SUM  =String.format("%.1f", sum);
        Random r=new Random();
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request1 = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request1.setReturnUrl(RETURN_URL);
        request1.setNotifyUrl(NOTIFY_URL);

//        商户订单号，商户网站订单系统中唯一订单号，必填
//        生成随机Id
//        String out_trade_no = UUID.randomUUID().toString();
        String out_trade_no = new Date().toString();
        //付款金额，必填

        String OrderTotal=(Double)request.getAttribute("sum")+"";
        String OderNo=(String)request.getAttribute("oderNo");


        //订单名称，必填 可设置为手机商品
        String subject ="美妆商品";
        //商品描述，可写各个商品
        String body = new String();
        body = "尊敬的用户欢迎购买美妆商品";


        request1.setBizContent("{" +
                "\"out_trade_no\":\""+ OderNo +"\","
                + "\"total_amount\":\""+OrderTotal +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String form = "";
        try {
            form = alipayClient.pageExecute(request1).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(form);
       response.setContentType("text/html;charset=" + CHARSET);
        response.getWriter().write(form);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
       response.getWriter().close();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 this.doPost(request,response);
    }
}
