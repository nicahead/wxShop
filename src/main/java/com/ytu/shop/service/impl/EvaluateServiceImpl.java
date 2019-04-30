package com.ytu.shop.service.impl;

import com.ytu.shop.common.dto.EvaluateListDto;
import com.ytu.shop.common.dto.PageInfo;
import com.ytu.shop.domain.TbEvaluate;
import com.ytu.shop.mapper.TbEvaluateMapper;
import com.ytu.shop.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EvaluateServiceImpl extends BaseServiceImpl<TbEvaluate, TbEvaluateMapper> implements EvaluateService {

    @Autowired
    private TbEvaluateMapper tbEvaluateMapper;

    @Override
    public List<EvaluateListDto> getByItemId(String itemId) {
        return tbEvaluateMapper.getByItemId(itemId);
    }

    @Override
    public PageInfo<EvaluateListDto> getPage(String shopId, int start, int length) {
        PageInfo<EvaluateListDto> page = new PageInfo();
        int count = tbEvaluateMapper.getCount(shopId);
        page.setData(tbEvaluateMapper.getPage(shopId,start, length));
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        return page;
    }


    @Override
    public int getCount(String shopId) {
        return tbEvaluateMapper.getCount(shopId);
    }
}
