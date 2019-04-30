package com.ytu.shop.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDomain {

    private String id;
    private Date created;
    private Date updated;
    private Integer state;

}
