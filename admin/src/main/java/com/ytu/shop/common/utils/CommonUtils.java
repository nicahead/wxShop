package com.ytu.shop.common.utils;


import com.ytu.shop.common.dto.ItemDetailDto;
import com.ytu.shop.domain.TbOrder;
import com.ytu.shop.domain.TbOrderdetail;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

    /**
     * 生成订单编号
     *
     * @return
     */
    public static String getOrderNum() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
        String str1 = f.format(c.getTime()) + "";
        String str2 = (int) ((Math.random() * 9 + 1) * 1000) + "";
        return str1 + str2;
    }

    /**
     * 商品列表转订单详情列表
     *
     * @param itemls
     * @return
     */
    public static List<TbOrderdetail> item2detail(List<ItemDetailDto> itemls, TbOrder order) {
        List<TbOrderdetail> detail = new ArrayList<TbOrderdetail>();
        TbOrderdetail tbOrderdetail;
        for (ItemDetailDto item : itemls) {
            tbOrderdetail = new TbOrderdetail();
            tbOrderdetail.setId(UUID.randomUUID().toString());
            tbOrderdetail.setItemId(item.getId());
            tbOrderdetail.setItemName(item.getItemName());
            tbOrderdetail.setPrice(item.getPrice());
            tbOrderdetail.setAmount(item.getNum());
            tbOrderdetail.setMoney(item.getPrice()*item.getNum());
            tbOrderdetail.setPic(item.getPic());
            tbOrderdetail.setOrderId(order.getId());
            detail.add(tbOrderdetail);
        }
        return detail;
    }


}
