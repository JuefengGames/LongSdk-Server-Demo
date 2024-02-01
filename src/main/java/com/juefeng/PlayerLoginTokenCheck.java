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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Token verification operation will be triggered immediately
 * after the player logs in the game to verify the reliability of the login information
 *
 * Developer --> JuefengGames
 */
public class PlayerLoginTokenCheck {


    /**
     *  error return: {"status":"12","msg":"sign error","openid":null,"serverNo":null}
     *  success return:
     * @param args
     */
    public static void main(String[] args) {
        String app_id = KeysConfig.APP_ID;
        String mem_id = "92362503";
        String user_token = "JFMIXTK_2040618_80656_53650074";
        String sign = "";
        String sourceStr = "app_id="+app_id+
                "&mem_id="+mem_id+
                "&user_token="+user_token+"&app_key="+KeysConfig.SERVER_KEY;
        System.out.println("before sourceStr ="+sourceStr);
        sign = DigestUtils.md5Hex(sourceStr);
        System.out.println("after sign ="+sign);
        try {
            HttpPost httpPost = new HttpPost(KeysConfig.TOKEN_CHECK_API_URL);
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            // Reqest parameters
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("app_id",     app_id));
            params.add(new BasicNameValuePair("mem_id",     mem_id));
            params.add(new BasicNameValuePair("user_token", user_token));
            params.add(new BasicNameValuePair("sign",       sign));
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
