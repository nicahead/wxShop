package com.ytu.shop.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ytu.shop.domain.BaseDomain;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EvaluateListDto extends BaseDomain {

    private String nickname;
    private String avatar;
    private Integer grade;
    private String content;

    private String orderId;
    private String orderNum;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date created;

}
