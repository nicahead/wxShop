package com.ytu.shop.service;

import com.ytu.shop.domain.TbSystem;

public interface SystemService{

    public int update(TbSystem tbSystem);

    public TbSystem select();
}
