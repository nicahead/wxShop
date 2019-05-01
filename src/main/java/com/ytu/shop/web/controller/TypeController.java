package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.domain.TbType;
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

import java.util.UUID;

@Controller
@RequestMapping("admin/type")
public class TypeController extends BaseController<TbType,TypeService> {

    @Autowired
    private TypeService typeService;

    @ModelAttribute("editType")
    public TbType editType(String id) {
        TbType editType = new TbType();
        if (StringUtils.isNotBlank(id)) {
            editType = typeService.selectOne(id);
        }
        return editType;
    }

    @GetMapping(value = "list")
    public String list() {
        return "admin/type/list";
    }

    @GetMapping(value = "form")
    public String main() {
        return "admin/type/form";
    }

    @PostMapping(value = "save")
    public String save(TbType tbType, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = new BaseResult();
        int res = 0;
        baseResult = check(tbType);
        if (baseResult.getStatus()==200){
            //修改操作
            if (StringUtils.isNotBlank(tbType.getId())) {
                res = typeService.update(tbType);
            }
            //新增操作
            else {
                if (typeService.getByName(tbType.getTypeName())!=null){
                    baseResult.setMessage("该类别已存在");
                    model.addAttribute("baseResult", baseResult);
                    return "admin/type/form";
                }
                tbType.setId(UUID.randomUUID().toString());
                res = typeService.insert(tbType);
            }
            if (res>0){
                baseResult.setMessage("操作成功");
                model.addAttribute("baseResult", baseResult);
                return "admin/type/list";
            }
        }
        model.addAttribute("baseResult", baseResult);
        return "admin/type/form";

    }

    private BaseResult check(TbType tbType){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(500);
        if (StringUtils.isBlank(tbType.getTypeName())){
            baseResult.setMessage("分类名不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbType.getDesp())){
            baseResult.setMessage("描述不能为空");
            return baseResult;
        }
        baseResult.setStatus(200);
        return baseResult;
    }

}
