package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbType;
import org.springframework.stereotype.Repository;

@Repository
public interface TbTypeMapper extends BaseMapper<TbType> {

    TbType getByName(String typeName);

}