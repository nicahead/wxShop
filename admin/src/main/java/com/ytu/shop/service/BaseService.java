package com.ytu.shop.service;

import com.ytu.shop.common.dto.PageInfo;
import com.ytu.shop.domain.BaseDomain;

import java.util.List;

public interface BaseService<T extends BaseDomain> {
    int insert(T t);

    int delete(String id);

    int deleteMulti(String[] ids);

    int update(T t);

    int count(T t);

    T selectOne(String id);

    List<T> selectAll();

    PageInfo<T> page(int start, int length, T t);
}
