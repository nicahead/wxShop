package com.ytu.shop.service;

import com.ytu.shop.domain.TbShop;

public interface ShopService extends BaseService<TbShop> {

    TbShop getByCode(String loginCode);
}
