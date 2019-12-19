package com.ytu.shop.service.impl;

import com.ytu.shop.common.dto.ItemDetailDto;
import com.ytu.shop.common.dto.ItemListDto;
import com.ytu.shop.domain.TbItem;
import com.ytu.shop.domain.TbOrderdetail;
import com.ytu.shop.mapper.TbItemMapper;
import com.ytu.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl extends BaseServiceImpl<TbItem,TbItemMapper> implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public List<ItemListDto> getList(TbItem tbItem) {
        return tbItemMapper.getList(tbItem);
    }

    @Override
    public ItemDetailDto getDetail(String id) {
        return tbItemMapper.getDetail(id);
    }

    @Override
    public List<ItemListDto> getNew() {
        return tbItemMapper.getNew();
    }

    @Override
    public int updateSale(TbOrderdetail tbOrderdetail) {
        return tbItemMapper.updateSale(tbOrderdetail);
    }
}
