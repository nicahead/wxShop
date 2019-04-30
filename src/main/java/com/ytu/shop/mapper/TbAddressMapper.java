package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbAddressMapper extends BaseMapper<TbAddress> {

    public List<TbAddress> getByUserId(String userId);

}