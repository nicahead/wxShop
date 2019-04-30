package com.ytu.shop.service;

import com.ytu.shop.domain.TbAddress;

import java.util.List;

public interface AddressService extends BaseService<TbAddress> {
    public List<TbAddress> getByUserId(String userId);
}
