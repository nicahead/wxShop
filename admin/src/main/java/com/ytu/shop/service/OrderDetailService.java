package com.ytu.shop.service;

import com.ytu.shop.domain.TbOrderdetail;

import java.util.List;

public interface OrderDetailService extends BaseService<TbOrderdetail> {

    public List<TbOrderdetail> getByOrderId(String orderId);
}
