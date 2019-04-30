package com.ytu.shop.service;

import com.ytu.shop.common.dto.EvaluateListDto;
import com.ytu.shop.common.dto.PageInfo;
import com.ytu.shop.domain.TbEvaluate;

import java.util.List;

public interface EvaluateService extends BaseService<TbEvaluate> {

    public List<EvaluateListDto> getByItemId(String itemId);

    public PageInfo<EvaluateListDto> getPage(String shopId, int start, int length);

    public int getCount(String shopId);
}
