package com.ytu.shop.domain;

import lombok.Data;

@Data
public class TbItem extends BaseDomain {

    private String itemName;
    private Float price;
    private Float packFee;  //派送费
    private String pic;
    private Long sale;
    private int stock;  //有货为1 售罄为0
    private String detail;
    private String shopId;
    private String typeId;

}