package com.ytu.shop.domain;

import lombok.Data;

@Data
public class TbEvaluate extends BaseDomain {

    private String userId;
    private Integer grade;
    private String content;
    private String orderId;

}
