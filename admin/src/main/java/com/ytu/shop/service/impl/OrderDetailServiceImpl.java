package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbOrderdetail;
import com.ytu.shop.mapper.TbOrderDetailMapper;
import com.ytu.shop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderDetailServiceImpl extends BaseServiceImpl<TbOrderdetail, TbOrderDetailMapper> implements OrderDetailService {

    @Autowired
    private TbOrderDetailMapper tbOrderDetailMapper;

    @Override
    public List<TbOrderdetail> getByOrderId(String orderId) {
        return tbOrderDetailMapper.getByOrderId(orderId);
    }
}
