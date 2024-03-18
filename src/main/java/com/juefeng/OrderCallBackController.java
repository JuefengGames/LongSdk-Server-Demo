package com.juefeng;

import com.juefeng.config.KeysConfig;
import com.juefeng.pojo.OrderInfoPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class OrderCallBackController {

    /**
     * Players purchase props in the game, and Juefeng Games will call this API after completing the payment.
     * For API-related parameters and signature rules, please check the API documentation.
     *
     * JuefengGames --> Developer
     *
     *  Please note that the parameters are not in JSON format, so please receive them directly.
     *  like： request.getParameter("sign")
     *
     *  TestCase see：TestSentCallBack.java
     */
    @ResponseBody
    @RequestMapping("/jfGame/callback")
    public String callBack(OrderInfoPojo infoPojo, HttpServletRequest request) {
        log.info("-------- params:{}",infoPojo);
        // System.out.println(request.getParameter("sign")); //sign
        String signFromJueFeng = infoPojo.getSign();
        String devSignStr =
                "amount="+infoPojo.getAmount()+
                "&gameArea="+infoPojo.getGameArea()+
                "&gameRole="+infoPojo.getGameRole()+
                "&orderId="+infoPojo.getOrderId()+
                "&payTime="+infoPojo.getPayTime()+
                "&payWay="+infoPojo.getPayWay()+
                "&productDesc="+infoPojo.getProductDesc()+
                "&productName="+infoPojo.getProductName()+
                "&remark="+infoPojo.getRemark()+
                KeysConfig.SERVER_KEY;

        System.out.println(" before sign deSignStr="+ devSignStr);
        String devSign = DigestUtils.md5Hex(devSignStr);
        System.out.println(" after sign devSign="+ devSign);

        if (signFromJueFeng.equalsIgnoreCase(devSign)) {
            log.info("-------- sign is ok");
            /**
             *
             *  Do somthing
             *
             */
            return "success";
        } else {
            log.info("-------- sign is error");
        }
        return "failed";
    }


}
