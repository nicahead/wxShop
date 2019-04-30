package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbType;
import com.ytu.shop.mapper.TbTypeMapper;
import com.ytu.shop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TypeServiceImpl extends BaseServiceImpl<TbType,TbTypeMapper> implements TypeService {

    @Autowired
    private TbTypeMapper tbTypeMapper;

    @Override
    public TbType getByName(String typeName) {
        return tbTypeMapper.getByName(typeName);
    }
}
