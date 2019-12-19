package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbShop;
import com.ytu.shop.mapper.TbShopMapper;
import com.ytu.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ShopServiceImpl extends BaseServiceImpl<TbShop,TbShopMapper> implements ShopService {

    @Autowired
    private TbShopMapper tbShopMapper;

    @Override
    public TbShop getByCode(String loginCode) {
        return tbShopMapper.getByCode(loginCode);
    }
}
