package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbSystem;
import org.springframework.stereotype.Repository;

@Repository
public interface TbSystemMapper{

    public int update(TbSystem tbSystem);

    public TbSystem select();

}