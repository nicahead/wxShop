package com.ytu.shop.service;

import com.ytu.shop.common.dto.ItemDetailDto;
import com.ytu.shop.common.dto.ItemListDto;
import com.ytu.shop.domain.TbItem;
import com.ytu.shop.domain.TbOrderdetail;

import java.util.List;

public interface ItemService extends BaseService<TbItem> {

    List<ItemListDto> getList(TbItem tbItem);

    ItemDetailDto getDetail(String id);

    List<ItemListDto> getNew();

    int updateSale(TbOrderdetail tbOrderdetail);

}
