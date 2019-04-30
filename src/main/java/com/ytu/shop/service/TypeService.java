package com.ytu.shop.service;

import com.ytu.shop.domain.TbType;

public interface TypeService extends BaseService<TbType> {

    TbType getByName(String typeName);

}
