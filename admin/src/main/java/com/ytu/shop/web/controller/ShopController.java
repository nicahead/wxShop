package com.ytu.shop.web.controller;

import com.ytu.shop.common.Constants;
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

import java.util.UUID;

@Controller
@RequestMapping("admin/shop")
public class ShopController extends BaseController<TbShop,ShopService> {

    @Autowired
    private ShopService shopService;

    @ModelAttribute("editShop")
    public TbShop editShop(String id) {
        TbShop editShop = new TbShop();
        if (StringUtils.isNotBlank(id)) {
            editShop = shopService.selectOne(id);
        }
        return editShop;
    }

    @GetMapping(value = "list")
    public String list() {
        return "admin/shop/list";
    }

    @GetMapping(value = "form")
    public String form() {
        return "admin/shop/form";
    }

    @PostMapping(value = "save")
    public String save(TbShop tbShop, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = new BaseResult();
        int res = 0;
        if (StringUtils.isBlank(tbShop.getShopLogo()))
            tbShop.setShopLogo(Constants.DEFAULT_AVATAR);
        baseResult = check(tbShop);
        if (baseResult.getStatus()==200){
            if (StringUtils.isNotBlank(tbShop.getPassword()))
                tbShop.setPassword(DigestUtils.md5DigestAsHex(tbShop.getPassword().getBytes()));
            //修改操作
            if (StringUtils.isNotBlank(tbShop.getId())) {
                res = shopService.update(tbShop);
            }
            //新增操作
            else {
                if (shopService.getByCode(tbShop.getLoginCode())!=null){
                    baseResult.setMessage("账号已存在");
                    model.addAttribute("baseResult", baseResult);
                    return "admin/shop/form";
                }
                if (StringUtils.isBlank(tbShop.getPassword())){
                    baseResult.setMessage("密码不能为空");
                    model.addAttribute("baseResult", baseResult);
                    return "admin/shop/form";
                }
                tbShop.setId(UUID.randomUUID().toString());
                res = shopService.insert(tbShop);
            }
            if (res>0){
                baseResult.setMessage("操作成功");
                model.addAttribute("baseResult", baseResult);
                return "admin/shop/list";
            }
        }
        model.addAttribute("baseResult", baseResult);
        return "admin/shop/form";

    }

    private BaseResult check(TbShop tbShop){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(500);
        if (StringUtils.isBlank(tbShop.getLoginCode())){
            baseResult.setMessage("账号不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbShop.getShopName())){
            baseResult.setMessage("商户名不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbShop.getLegalPerson())){
            baseResult.setMessage("法人不能为空");
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
