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

/**
 *
 * Token verification operation will be triggered immediately
 * after the player logs in the game to verify the reliability of the login information
 *
 * Developer --> JuefengGames
 */
public class PlayerLoginTokenCheck {


    /**
     *  error return: {"status":"12","msg":"sign error","openid":null,"serverNo":null}
     *  success return: {"status":"1","msg":"success","openid":null,"serverNo":""}
     */
    public static String checkToken(String appId, String memId, String userToken){
        String sign = "";
        // start sign
        String sourceStr = "app_id="+ appId +"&mem_id="+ memId +"&user_token="+ userToken +"&app_key="+KeysConfig.SERVER_KEY;
        System.out.println("before sourceStr ="+sourceStr);
        sign = DigestUtils.md5Hex(sourceStr);
        System.out.println("after sign ="+sign);
        // start request server
        try {
            HttpPost httpPost = new HttpPost(KeysConfig.TOKEN_CHECK_API_URL);
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            // parameters
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("app_id", appId));
            params.add(new BasicNameValuePair("mem_id", memId));
            params.add(new BasicNameValuePair("user_token", userToken));
            params.add(new BasicNameValuePair("sign",       sign));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            String rs = EntityUtils.toString(execute.getEntity());
            System.out.println("rs="+rs);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
            checkToken(KeysConfig.APP_ID, "92362503", "JFMIXTK_2040618_80656_53650074");
    }

}
