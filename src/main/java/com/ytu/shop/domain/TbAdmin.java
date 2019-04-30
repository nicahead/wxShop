package com.ytu.shop.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TbAdmin extends BaseDomain {

    private String loginCode;
    private String password;
    private String username;
    private String email;
    private Integer role;
    private Date logined;
}