package com.ytu.shop.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TbShop extends BaseDomain {

    private String loginCode;
    private String password;
    private String shopName;
    private String shopLogo;
    private String shopBg;
    private String descp;
    private String legalPerson;
    private String tel;
    private Date logined;

}