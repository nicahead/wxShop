package com.ytu.shop.domain;

import lombok.Data;

@Data
public class TbOrderdetail extends BaseDomain {
    private String itemId;
    private String itemName;
    private String pic;
    private Float packFee;
    private Float price;
    private Integer amount;
    private Float money;
    private String orderId;
    private String orderNum;
    private String shopId;
    private String shopName;
}
