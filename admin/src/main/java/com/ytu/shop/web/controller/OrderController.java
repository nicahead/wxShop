package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.domain.TbOrder;
import com.ytu.shop.domain.TbOrderdetail;
import com.ytu.shop.service.OrderDetailService;
import com.ytu.shop.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("shop/order")
public class OrderController extends BaseController<TbOrder,OrderService> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(value = "undone")
    public String undone() {
        return "shop/order/undone";
    }

    @GetMapping(value = "doing")
    public String doing() {
        return "shop/order/doing";
    }

    @GetMapping(value = "done")
    public String done() {
        return "shop/order/done";
    }

    @ResponseBody
    @PostMapping(value = "deliver")
    public BaseResult deliver(String ids) {
        BaseResult baseResult = new BaseResult();
        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            if (orderService.deliverMulti(idArray)>0)
                baseResult.setMessage("修改成功");
        }else{
            baseResult = BaseResult.fail("修改成功");
        }
        return baseResult;
    }

    @ResponseBody
    @PostMapping(value = "getDetail")
    public List<TbOrderdetail> getDetail(String orderId) {
        return orderDetailService.getByOrderId(orderId);
    }

}
