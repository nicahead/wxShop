package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbAddress;
import com.ytu.shop.mapper.TbAddressMapper;
import com.ytu.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl extends BaseServiceImpl<TbAddress, TbAddressMapper> implements AddressService {

    @Autowired
    private TbAddressMapper tbAddressMapper;

    @Override
    public List<TbAddress> getByUserId(String userId) {
        return tbAddressMapper.getByUserId(userId);
    }
}
