package com.ytu.shop.domain;

import lombok.Data;

import java.util.List;

@Data
public class TbOrder extends BaseDomain {
    private String orderNum;
    private String shopId;
    private String shopName;
    private String userId;
    private String addressId;

    private List<TbOrderdetail> detail;
    private Float money;
    private Float packFee;

}
