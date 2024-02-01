package com.juefeng;

import com.juefeng.pojo.OrderInfoPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class OrderCallBackController {

    /**
     *Players purchase props in the game, and Juefeng Games will call this API after completing the payment.
     * For API-related parameters and signature rules, please check the API documentation.
     *
     * JuefengGames --> Developer
     *
     */
    @ResponseBody
    @RequestMapping("/jfapi/callback")
    public String callBack(OrderInfoPojo infoPojo) {
        log.info("-------- params:{}",infoPojo);




        return "ok";
    }


}
