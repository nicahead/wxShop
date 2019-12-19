package com.ytu.shop.mapper;

import com.ytu.shop.common.dto.EvaluateListDto;
import com.ytu.shop.domain.TbEvaluate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbEvaluateMapper extends BaseMapper<TbEvaluate> {

    public List<EvaluateListDto> getByItemId(String itemId);

    public List<EvaluateListDto> getPage(@Param("shopId")String shopId, @Param("start")int start, @Param("length")int length);

    public int getCount(@Param("shopId")String shopId);

}