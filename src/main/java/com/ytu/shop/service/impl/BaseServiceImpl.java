package com.ytu.shop.service.impl;

import com.ytu.shop.common.dto.PageInfo;
import com.ytu.shop.domain.BaseDomain;
import com.ytu.shop.mapper.BaseMapper;
import com.ytu.shop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T extends BaseDomain, D extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public int deleteMulti(String[] ids) {
        return dao.deleteMulti(ids);
    }

    @Override
    public int update(T t) {
        return dao.update(t);
    }

    @Override
    public int count(T t) {
        return dao.count(t);
    }

    @Override
    public T selectOne(String id) {
        return dao.getById(id);
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public PageInfo<T> page(int start, int length, T t) {
        PageInfo<T> page = new PageInfo();
        int count = dao.count(t);
        page.setData(dao.page(start, length, t));
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        return page;
    }
}