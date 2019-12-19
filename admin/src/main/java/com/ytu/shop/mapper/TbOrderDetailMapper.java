package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbOrderdetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbOrderDetailMapper extends BaseMapper<TbOrderdetail> {

    public List<TbOrderdetail> getByOrderId(String orderId);
}