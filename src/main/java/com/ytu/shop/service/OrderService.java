package com.ytu.shop.service;

import com.ytu.shop.domain.TbOrder;

import java.util.List;

public interface OrderService extends BaseService<TbOrder> {

    public List<TbOrder> getByUserId(TbOrder tbOrder);

    public int deliverMulti(String[] ids);

}
