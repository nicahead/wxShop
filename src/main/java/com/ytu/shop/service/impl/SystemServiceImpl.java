package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbSystem;
import com.ytu.shop.mapper.TbSystemMapper;
import com.ytu.shop.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private TbSystemMapper tbSystemMapper;


    @Override
    public int update(TbSystem tbSystem) {
        return tbSystemMapper.update(tbSystem);
    }

    @Override
    public TbSystem select() {
        return tbSystemMapper.select();
    }
}
