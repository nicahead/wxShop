package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.BaseResult;
import com.ytu.shop.common.dto.PageInfo;
import com.ytu.shop.domain.BaseDomain;
import com.ytu.shop.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController<T extends BaseDomain, S extends BaseService> {

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    @ResponseBody
    @GetMapping(value = "page")
    public PageInfo page(HttpServletRequest request, T t) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        PageInfo pageInfo = service.page(start, length, t);
        int count = service.count(t);
        try {
            pageInfo.setDraw(draw);
            pageInfo.setRecordsTotal(count);
            pageInfo.setRecordsFiltered(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResult delete(String ids) {
        BaseResult baseResult = new BaseResult();
        if (!StringUtils.isBlank(ids)){
            String[] idArray = ids.split(",");
            if (service.deleteMulti(idArray)>0)
                baseResult.setMessage("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }
}
