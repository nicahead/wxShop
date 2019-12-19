package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbOrder;
import com.ytu.shop.mapper.TbOrderMapper;
import com.ytu.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<TbOrder, TbOrderMapper> implements OrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public List<TbOrder> getByUserId(TbOrder tbOrder) {
        return tbOrderMapper.getByUserId(tbOrder);
    }

    @Override
    public int deliverMulti(String[] ids) {
        return tbOrderMapper.deliverMulti(ids);
    }
}
