package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.domain.TbItem;
import com.ytu.shop.domain.TbType;
import com.ytu.shop.service.ItemService;
import com.ytu.shop.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("shop/item")
public class ItemController extends BaseController<TbItem,ItemService> {

    @Autowired
    private ItemService itemService;

    @Autowired
    private TypeService typeService;

    @ModelAttribute("editItem")
    public TbItem editItem(String id) {
        TbItem editItem = new TbItem();
        if (StringUtils.isNotBlank(id)) {
            editItem = itemService.selectOne(id);
        }
        return editItem;
    }

    @ModelAttribute("typels")
    public List<TbType> typels(String id) {
        return typeService.selectAll();
    }

    @GetMapping(value = "list")
    public String list() {
        return "shop/item/list";
    }

    @GetMapping(value = "form")
    public String form() {
        return "shop/item/form";
    }

    @PostMapping(value = "save")
    public String save(TbItem tbItem, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = new BaseResult();
        int res = 0;
        baseResult = check(tbItem);
        if (baseResult.getStatus()==200){
            //修改操作
            if (StringUtils.isNotBlank(tbItem.getId())) {
                res = itemService.update(tbItem);
            }
            //新增操作
            else {
                tbItem.setId(UUID.randomUUID().toString());
                res = itemService.insert(tbItem);
            }
            if (res>0){
                baseResult.setMessage("操作成功");
                model.addAttribute("baseResult", baseResult);
                return "shop/item/list";
            }
        }
        baseResult.setMessage("操作失败");
        model.addAttribute("baseResult", baseResult);
        return "shop/item/form";

    }

    private BaseResult check(TbItem tbItem){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(500);
        if (StringUtils.isBlank(tbItem.getItemName())){
            baseResult.setMessage("商品名不能为空");
            return baseResult;
        }
        if (tbItem.getPrice()==null){
            baseResult.setMessage("价格不能为空");
            return baseResult;
        }
        if (tbItem.getPackFee()==null){
            baseResult.setMessage("派送费不能为空");
            return baseResult;
        }
        if (tbItem.getPic()==null){
            baseResult.setMessage("请上传商品图片");
            return baseResult;
        }
        baseResult.setStatus(200);
        return baseResult;
    }

}
