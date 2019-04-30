package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.common.dto.EvaluateListDto;
import com.ytu.shop.common.dto.ItemDetailDto;
import com.ytu.shop.common.dto.ItemListDto;
import com.ytu.shop.common.utils.CommonUtils;
import com.ytu.shop.common.utils.MapperUtils;
import com.ytu.shop.domain.*;
import com.ytu.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private ShopService shopService;

    @PostMapping(value = "adduser")
    public int adduser(TbUser tbUser) {
        if (userService.selectOne(tbUser.getId())==null)
            return userService.insert(tbUser);
        return 0;
    }

    @PostMapping(value = "addAddress")
    public int addAddress(TbAddress tbAddress) {
        tbAddress.setId(UUID.randomUUID().toString());
        return addressService.insert(tbAddress);
    }

    @PostMapping(value = "getAddressls")
    public List<TbAddress> getAddressls(String userId) {
        return addressService.getByUserId(userId);
    }

    @PostMapping(value = "getAddress")
    public TbAddress getAddress(String id) {
        return addressService.selectOne(id);
    }

    @PostMapping(value = "updateAddress")
    public int updateAddress(TbAddress tbAddress) {
        return addressService.update(tbAddress);
    }

    @PostMapping(value = "delAddress")
    public int delAddress(String id) {
        return addressService.delete(id);
    }

    @PostMapping(value = "typels")
    public List<TbType> typels() {
        return typeService.selectAll();
    }

    @PostMapping(value = "getList")
    public List<ItemListDto> getItemByTid(TbItem tbItem) {
        if (("0").equals(tbItem.getTypeId()))
            tbItem.setTypeId(null);
        return itemService.getList(tbItem);
    }

    @PostMapping(value = "getNew")
    public List<ItemListDto> getNew() {
        return itemService.getNew();
    }

    @PostMapping(value = "itemDetail")
    public ItemDetailDto itemDetail(String id) {
        return itemService.getDetail(id);
    }

    @PostMapping(value = "addorder")
    public List<TbOrder> addorder(String userId, String addressId, String detailstr, String type) throws Exception {
        List<ItemDetailDto> itemls = new ArrayList<ItemDetailDto>();
        List<TbOrder> orderls = new ArrayList<TbOrder>();
        //一件商品
        if ("buyNow".equals(type)) {
            ItemDetailDto itemDetailDto = MapperUtils.json2pojo(detailstr, ItemDetailDto.class);
            TbOrder tbOrder = new TbOrder();
            orderls.add(tbOrder);
            tbOrder.setId(UUID.randomUUID().toString());
            tbOrder.setOrderNum(CommonUtils.getOrderNum());
            tbOrder.setShopId(itemDetailDto.getShopId());
            tbOrder.setShopName(itemDetailDto.getShopName());
            tbOrder.setUserId(userId);
            tbOrder.setAddressId(addressId);
            orderService.insert(tbOrder);

            TbOrderdetail newDetail = new TbOrderdetail();
            newDetail.setId(UUID.randomUUID().toString());
            newDetail.setItemId(itemDetailDto.getId());
            newDetail.setItemName(itemDetailDto.getItemName());
            newDetail.setPic(itemDetailDto.getPic());
            newDetail.setPrice(itemDetailDto.getPrice());
            newDetail.setAmount(itemDetailDto.getNum());
            newDetail.setMoney(itemDetailDto.getPrice() * itemDetailDto.getNum());
            newDetail.setShopId(itemDetailDto.getShopId());
            newDetail.setShopName(itemDetailDto.getShopName());
            newDetail.setOrderId(tbOrder.getId());
            newDetail.setOrderNum(tbOrder.getOrderNum());

            orderDetailService.insert(newDetail);
        }
        //多件商品
        else {
            boolean flag = false;
            itemls = MapperUtils.json2list(detailstr, ItemDetailDto.class);  //商品列表
            List<TbOrderdetail> dones = new ArrayList<TbOrderdetail>();
            for (ItemDetailDto undone : itemls) {
                TbOrderdetail newDetail = new TbOrderdetail();
                newDetail.setId(UUID.randomUUID().toString());
                newDetail.setItemId(undone.getId());
                newDetail.setItemName(undone.getItemName());
                newDetail.setPic(undone.getPic());
                newDetail.setPrice(undone.getPrice());
                newDetail.setAmount(undone.getNum());
                newDetail.setMoney(undone.getPrice() * undone.getNum());
                newDetail.setShopId(undone.getShopId());
                newDetail.setShopName(undone.getShopName());

                for (TbOrderdetail done : dones) {
                    //存在该店铺，不需要新实例化一个订单
                    if (undone.getShopId().equals(done.getShopId())) {
                        newDetail.setOrderId(done.getOrderId());
                        newDetail.setOrderNum(done.getOrderNum());
                        orderDetailService.insert(newDetail);
                        flag = true;
                        break;
                    }
                }
                //不存在该店铺，需要新实例化一个订单
                if (flag == false){
                    TbOrder newOrder = new TbOrder();
                    orderls.add(newOrder);
                    newOrder.setId(UUID.randomUUID().toString());
                    newOrder.setOrderNum(CommonUtils.getOrderNum());
                    newOrder.setShopId(undone.getShopId());
                    newOrder.setShopName(undone.getShopName());
                    newOrder.setUserId(userId);
                    newOrder.setAddressId(addressId);
                    orderService.insert(newOrder);

                    newDetail.setOrderId(newOrder.getId());
                    newDetail.setOrderNum(newOrder.getOrderNum());

                    orderDetailService.insert(newDetail);
                }
                dones.add(newDetail);
                flag = false;
            }
        }
        return orderls;
    }

    @PostMapping(value = "orderOp")
    public BaseResult orderOp(String orderstr,Integer state,String type) throws Exception {
        BaseResult baseResult = new BaseResult();
        if ("single".equals(type)){
            TbOrder order = MapperUtils.json2pojo(orderstr,TbOrder.class);
            order.setState(state);
            orderService.update(order);
            //如果是已付款订单
            if (state == 2){
                List<TbOrderdetail> deatilList = orderDetailService.getByOrderId(order.getId());
                for (TbOrderdetail detail:deatilList)
                    itemService.updateSale(detail);
            }
        }else{
            List<TbOrder> orderls = MapperUtils.json2list(orderstr,TbOrder.class);
            for (TbOrder order:orderls){
                order.setState(state);
                orderService.update(order);
                //如果是已付款订单
                if (state == 2){
                    List<TbOrderdetail> deatilList = orderDetailService.getByOrderId(order.getId());
                    for (TbOrderdetail detail:deatilList)
                        itemService.updateSale(detail);
                }
            }
        }
        baseResult.setMessage("修改成功");
        return baseResult;
    }

    @PostMapping(value = "orderls")
    public List<TbOrder> orderls(TbOrder tbOrder) {
        List<TbOrder> orderls = orderService.getByUserId(tbOrder);
        List<TbOrderdetail> detail = new ArrayList<TbOrderdetail>();
        for (TbOrder order : orderls) {
            detail = orderDetailService.getByOrderId(order.getId());
            float money = 0;
            float packFee = 0;
            for(TbOrderdetail d:detail){
                money += d.getMoney();
                packFee += d.getPackFee();
            }
            order.setMoney(money);
            order.setPackFee(packFee);
            order.setDetail(detail);
        }
        return orderls;
    }

    @PostMapping(value = "getOrderById")
    public TbOrder getOrderById(String id) {
        return orderService.selectOne(id);
    }

    @PostMapping(value = "evaluate")
    public BaseResult evaluate(TbEvaluate evaluate) {
        BaseResult baseResult = new BaseResult();
        evaluate.setId(UUID.randomUUID().toString());
        evaluateService.insert(evaluate);
        baseResult.setMessage("添加成功");
        return baseResult;
    }

    @PostMapping(value = "getEvaluatels")
    public List<EvaluateListDto> getEvaluatels(String itemId) {
        return evaluateService.getByItemId(itemId);
    }

    @PostMapping(value = "getShop")
    public TbShop getShop(String shopId) {
        return shopService.selectOne(shopId);
    }

}
