package com.juefeng.pojo;

import lombok.Data;

@Data
public class OrderInfoPojo {

    private String orderId;  // juefeng games order id，Globally unique

    private String gameRole; // game role identify

    private String gameArea; // game area identify

    private String productName; // VIP

    private String productDesc; // 100 diamonds

    private String remark;  // Developer order information, returned as is, maximum length 64 characters

    private String payTime; // 2024-01-13 12:15:30

    private String amount;  // 648.00 , 0.99, 100

    private String payWay;  // 1

    private String sign;

}
