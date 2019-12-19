package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.domain.TbAdmin;
import com.ytu.shop.service.AdminService;
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
@RequestMapping("admin/user")
public class AdminController extends BaseController<TbAdmin,AdminService> {

    @Autowired
    private AdminService adminService;

    @ModelAttribute("editUser")
    public TbAdmin tbUser(String id) {
        TbAdmin editUser = new TbAdmin();
        if (StringUtils.isNotBlank(id)) {
            editUser = adminService.selectOne(id);
        }
        return editUser;
    }

    @GetMapping(value = "list")
    public String list() {
        return "admin/user/list";
    }

    @GetMapping(value = "form")
    public String main() {
        return "admin/user/form";
    }

    @GetMapping(value = "myform")
    public String myform() {
        return "admin/user/myform";
    }

    @PostMapping(value = "save")
    public String save(TbAdmin tbAdmin, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = new BaseResult();
        int res = 0;
        baseResult = check(tbAdmin);
        if (baseResult.getStatus()==200){
            if (StringUtils.isNotBlank(tbAdmin.getPassword()))
                tbAdmin.setPassword(DigestUtils.md5DigestAsHex(tbAdmin.getPassword().getBytes()));
            //修改操作
            if (StringUtils.isNotBlank(tbAdmin.getId())) {
                res = adminService.update(tbAdmin);
            }
            //新增操作
            else {
                if (adminService.getByCode(tbAdmin.getLoginCode())!=null){
                    baseResult.setMessage("账号已存在");
                    model.addAttribute("baseResult", baseResult);
                    if (tbAdmin.getRole() == null)
                        return "admin/user/myform";
                    return "admin/user/form";
                }
                if (StringUtils.isBlank(tbAdmin.getPassword())){
                    baseResult.setMessage("密码不能为空");
                    model.addAttribute("baseResult", baseResult);
                    if (tbAdmin.getRole() == null)
                        return "admin/user/myform";
                    return "admin/user/form";
                }
                tbAdmin.setId(UUID.randomUUID().toString());
                res = adminService.insert(tbAdmin);
            }
            if (res>0){
                baseResult.setMessage("操作成功");
                model.addAttribute("baseResult", baseResult);
                return "admin/user/list";
            }
        }
        model.addAttribute("baseResult", baseResult);
        if (tbAdmin.getRole() == null)
            return "admin/user/myform";
        return "admin/user/form";
    }

    private BaseResult check(TbAdmin tbAdmin){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(500);
        if (StringUtils.isBlank(tbAdmin.getLoginCode())){
            baseResult.setMessage("账号不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbAdmin.getUsername())){
            baseResult.setMessage("用户名不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbAdmin.getEmail())){
            baseResult.setMessage("邮箱不能为空");
            return baseResult;
        }
        baseResult.setStatus(200);
        return baseResult;
    }

}
