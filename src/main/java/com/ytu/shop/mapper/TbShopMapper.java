package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbShop;
import org.springframework.stereotype.Repository;

@Repository
public interface TbShopMapper extends BaseMapper<TbShop> {

    TbShop getByCode(String loginCode);
}