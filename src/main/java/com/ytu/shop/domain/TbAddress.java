package com.ytu.shop.domain;

import lombok.Data;

@Data
public class TbAddress extends BaseDomain {

    private String receiver;
    private String tel;
    private String province;
    private String city;
    private String area;
    private String detail;
    private String userId;

}