package com.ytu.shop.common.dto;

import lombok.Data;

@Data
public class ItemListDto {

    private String id;
    private String itemName;
    private Float price;
    private String pic;
    private Long sale;
    private int stock;  //有货为1 售罄为0
    private String shopId;
    private String shopName;

}
