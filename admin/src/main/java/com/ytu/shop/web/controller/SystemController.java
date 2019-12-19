package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.domain.TbSystem;
import com.ytu.shop.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/sys")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @ModelAttribute("editSys")
    public TbSystem editSys() {
        return systemService.select();
    }

    @GetMapping(value = "banner")
    public String banner() {
        return "admin/sys/banner";
    }

    @GetMapping(value = "setup")
    public String setup() {
        return "admin/sys/setup";
    }

    @ResponseBody
    @PostMapping(value = "get")
    public TbSystem get() {
        return systemService.select();
    }

    @PostMapping(value = "update")
    public String update(TbSystem tbSystem, String type, Model model) {
        BaseResult baseResult = new BaseResult();
        int res = 0;
        if ("setup".equals(type)) {
            baseResult = check(tbSystem);
            if (baseResult.getStatus() == 200) {
                if (systemService.update(tbSystem) > 0) {
                    baseResult.setMessage("修改成功");
                } else {
                    baseResult.setMessage("修改失败");
                }
            }
            model.addAttribute("baseResult", baseResult);
            model.addAttribute("editSys", systemService.select());
            return "admin/sys/setup";
        } else {
            if (systemService.update(tbSystem) > 0) {
                baseResult.setMessage("修改成功");
            } else {
                baseResult.setMessage("修改失败");
            }
            model.addAttribute("baseResult", baseResult);
            model.addAttribute("editSys", systemService.select());
            return "admin/sys/banner";
        }
    }

    private BaseResult check(TbSystem tbSystem) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(500);
        if (StringUtils.isBlank(tbSystem.getAppID())) {
            baseResult.setMessage("AppID不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbSystem.getAppSecret())) {
            baseResult.setMessage("AppSecret不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbSystem.getAppName())) {
            baseResult.setMessage("小程序名不能为空");
            return baseResult;
        }
        baseResult.setStatus(200);
        return baseResult;
    }

}
