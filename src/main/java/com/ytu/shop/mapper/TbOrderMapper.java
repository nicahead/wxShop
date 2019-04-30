package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbOrderMapper extends BaseMapper<TbOrder> {

    public List<TbOrder> getByUserId(TbOrder tbOrder);

    public int deliverMulti(String[] ids);

}