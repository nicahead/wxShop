package com.ytu.shop.common.dto;

import com.ytu.shop.domain.BaseDomain;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfo<T extends BaseDomain> extends BaseResult implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
}
