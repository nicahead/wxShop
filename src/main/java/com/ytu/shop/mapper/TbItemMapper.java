package com.ytu.shop.mapper;

import com.ytu.shop.common.dto.ItemDetailDto;
import com.ytu.shop.common.dto.ItemListDto;
import com.ytu.shop.domain.TbItem;
import com.ytu.shop.domain.TbOrderdetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbItemMapper extends BaseMapper<TbItem> {

    List<ItemListDto> getList(@Param("entity") TbItem tbItem);

    ItemDetailDto getDetail(String id);

    List<ItemListDto> getNew();

    int updateSale(TbOrderdetail tbOrderdetail);

}