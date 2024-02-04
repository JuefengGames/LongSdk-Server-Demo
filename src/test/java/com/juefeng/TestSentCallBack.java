package com.juefeng;

import com.juefeng.config.KeysConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class TestSentCallBack {


    public static void main(String[] args) {

        String amount = "68.0";
        String gameArea = "195";
        String gameRole = "35734";
        String orderId = "ZFB20240204141435717198";
        String payTime = "2024-02-04 14:15:00";
        String payWay = "10";
        String productName = "100 diamonds";
        String productDesc = "100 diamonds";
        String remark = "31991TJUEFENG7Fe3N630347256561664";

        String devSignStr =
                        "amount="+amount+
                        "&gameArea="+gameArea+
                        "&gameRole="+gameRole+
                        "&orderId="+orderId+
                        "&payTime="+payTime+
                        "&payWay="+payWay+
                        "&productDesc="+productDesc+
                        "&productName="+productName+
                        "&remark="+remark+
                        KeysConfig.SERVER_KEY;

        String sign = DigestUtils.md5Hex(devSignStr);

        try {
            HttpPost httpPost = new HttpPost("http://localhost:8888/jfGame/callback");
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            // parameters
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("amount",      amount));
            params.add(new BasicNameValuePair("gameArea",    gameArea));
            params.add(new BasicNameValuePair("gameRole",    gameRole));
            params.add(new BasicNameValuePair("orderId",     orderId));
            params.add(new BasicNameValuePair("payTime",     payTime));
            params.add(new BasicNameValuePair("payWay",      payWay));
            params.add(new BasicNameValuePair("productDesc", productDesc));
            params.add(new BasicNameValuePair("productName", productName));
            params.add(new BasicNameValuePair("remark",      remark));
            params.add(new BasicNameValuePair("sign",        sign));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            String rs = EntityUtils.toString(execute.getEntity());
            System.out.println("rs="+rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
