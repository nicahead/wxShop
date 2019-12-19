package com.ytu.shop.web.controller;

import com.ytu.shop.domain.TbAdmin;
import com.ytu.shop.domain.TbShop;
import com.ytu.shop.service.AdminService;
import com.ytu.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ShopService shopService;

    @GetMapping(value = {"", "login"})
    public String login() {
        return "login";
    }

    @PostMapping(value = "login")
    public String login(String logincode,String password,String role,Model model,HttpServletRequest httpServletRequest){
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        //管理员登录
        if (role.equals("0")){
            TbAdmin tbAdmin = adminService.getByCode(logincode);
            if (tbAdmin!=null){
                //登录成功
                if (md5Password.equals(tbAdmin.getPassword())){
                    HttpSession session = httpServletRequest.getSession();
                    session.setAttribute("admin", tbAdmin);
                    tbAdmin.setLogined(new Date());
                    adminService.update(tbAdmin);
                    return "redirect:/admin/user/list";
                }
            }
        }
        //店铺登录
        else{
            TbShop tbShop = shopService.getByCode(logincode);
            if (tbShop!=null){
                //登录成功
                if (md5Password.equals(tbShop.getPassword())){
                    HttpSession session = httpServletRequest.getSession();
                    session.setAttribute("shop", tbShop);
                    session.setMaxInactiveInterval(60*60);
                    tbShop.setLogined(new Date());
                    shopService.update(tbShop);
                    return "redirect:/shop/item/list";
                }
            }
        }
        model.addAttribute("message", "用户名或密码错误，请重新输入");
        return "login";
    }

    @GetMapping(value = "logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return "login";
    }

}
