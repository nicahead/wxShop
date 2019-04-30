package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.domain.TbShop;
import com.ytu.shop.service.ShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("shop/my")
public class MyShopController extends BaseController<TbShop,ShopService> {

    @Autowired
    private ShopService shopService;

    @ModelAttribute("editShop")
    public TbShop editShop(HttpSession session) {
        TbShop editShop = (TbShop) session.getAttribute("shop");
        return editShop;
    }

    @GetMapping(value = "form")
    public String form() {
        return "shop/my/form";
    }

    @PostMapping(value = "save")
    public String save(TbShop tbShop, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = new BaseResult();
        int res = 0;
        baseResult = check(tbShop);
        if (baseResult.getStatus()==200){
            if (StringUtils.isNotBlank(tbShop.getPassword()))
                tbShop.setPassword(DigestUtils.md5DigestAsHex(tbShop.getPassword().getBytes()));
            if (shopService.update(tbShop)>0){
                baseResult.setMessage("操作成功");
                model.addAttribute("baseResult", baseResult);
                return "/shop/my/form";
            }
        }
        baseResult.setMessage("操作失败");
        model.addAttribute("baseResult", baseResult);
        return "/shop/my/form";

    }

    private BaseResult check(TbShop tbShop){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(500);
        if (StringUtils.isBlank(tbShop.getShopName())){
            baseResult.setMessage("商户名不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbShop.getLegalPerson())){
            baseResult.setMessage("店铺法人不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbShop.getTel())){
            baseResult.setMessage("电话号码不能为空");
            return baseResult;
        }
        baseResult.setStatus(200);
        return baseResult;
    }

}
