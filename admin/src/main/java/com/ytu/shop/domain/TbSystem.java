package com.ytu.shop.domain;

import lombok.Data;

@Data
public class TbSystem extends BaseDomain {

    private String appID;
    private String appSecret;
    private String appName;
    private String notice;
    private String about;
    private String contact;
    private String pic1;
    private String pic2;
    private String pic3;

}