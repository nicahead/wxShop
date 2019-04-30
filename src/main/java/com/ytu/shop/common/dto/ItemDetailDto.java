package com.ytu.shop.common.dto;

import lombok.Data;

@Data
public class ItemDetailDto extends ItemListDto {

    private Integer num = 0;
    private Float packFee;  //派送费
    private String detail;

}
